package controller;

import model.Loot;
import model.Pirate;
import model.Util;

import java.util.*;

/**
 * Controller of part I
 * Responsible for storing and manipulating Pirates data, the program mainly runs in this Class
 */

public class Controller {
    /**
     * Maximal number of pirate allowed
     */
    private static final int MAXPIRATES = 26;
    /**
     * Scanner class for interaction with user
     */
    private Scanner scanner;
    /**
     * List of Pirate, modified important data
     */
    private ArrayList<Pirate> pirates;
    /**
     * List of Loot, modified important data
     */
    private ArrayList<Loot> listOfLoot;
    /**
     * Number of Pirate
     */
    private int numberPirates;
    /**
     * Maximal char of loot allowed
     */
    private char maxCharAllowed;

    /**
     * @deprecated Constructor of part I, usually you do not need to execute it for part II.
     */
    public Controller(Scanner sc) {
        scanner = sc;
    }

    /**
     * Execute the program of the project in the terminal
     */
    public void runWithUserInteraction() {

        /*------------------------- Part I ---------------------------------
        - Letao                                                            -
        - Config a pirate crew with :  n pirates (designated as A,B,C ...) -
        - n loots ( designated as a number) TO BE TESTED with 26 pirates   -
        ------------------------------------------------------------------*/
        initializationWithInteraction();

        /*--------------------------Part II ----------------------------
        - Letao                                                        -
        - Menu with 2 options : Add a relation & Add a preference      -
        - Check if all the pirates have their preferences              -
        ------------------------------------------------------------- */
        addPreferenceAndRelationWithInteraction();

        /*------------------------- Part III ----------------------------
        - Paul                                                          -
        - III. Find a solution :                                        -
        - 1. Every pirate have their first preference if not the second -
        - 2. The lowest possible cost ( Une solution naı̈ve )            -
        -------------------------------------------------------------- */
        findNaiveSolutionWithInteraction();

        /* ------------------------ Part IV --------------------------------
        - Stan                                                             -
        - 1. Exchange the loot : Ask the two pirates and change their loot -
        - 2. Display the cost : The number of the jealous pirates          -
        - After each action, display the relation                          -
        ----------------------------------------------------------------- */
        exchangeLootAndDisplayWithInteraction();
    }

    /**
     * Config a pirate crew with :  n pirates (designated as A,B,C ...)
     * n loots ( designated as a number) TO BE TESTED with 26 pirates
     */
    private void initializationWithInteraction() {
        System.out.println("Welcome to Loot sharing !");
        System.out.println("Please enter the number of pirates: ");
        numberPirates = Util.getChoiceInt(MAXPIRATES, scanner);
        maxCharAllowed = (char) ('A' + numberPirates - 1);
        System.out.println();

        // Initialisation of the pirate list
        pirates = new ArrayList<>();
        for (int i = 0; i < numberPirates; i++) {
            char name = (char) ('A' + i);
            pirates.add(new Pirate(name));
        }
        for (Pirate p : pirates) {
            System.out.println("Pirate " + p.getName());
        }
        System.out.println(numberPirates + " Pirates have been initialized.");

        // Initialisation of the loot list
        listOfLoot = new ArrayList<>();
        for (int i = 1; i < numberPirates + 1; i++) {
            Loot l = new Loot(i);
            listOfLoot.add(l);
        }
        System.out.println(numberPirates + " Loots have been initialized \n");
    }

    private Boolean checkPreference(ArrayList<Pirate> pi, ArrayList<Loot> l) {
        for (Pirate p : pi) {
            if (p.getPreferenceList().size() != l.size()) {
                System.out.println("The pirate " + p.getName() + " didn't have a preference list");
                return false;
            }
        }
        return true;
    }

    /**
     * Menu with 2 options : Add a relation & Add a preference      -
     * Check if all the pirates have their preferences
     */
    private void addPreferenceAndRelationWithInteraction() {
        int choice;
        do {
            // Menu
            System.out.println("Choose your next action: ");
            System.out.println("1) ajouter une relation;");
            System.out.println("2) ajouter des préférences;");
            System.out.println("3) fin");
            choice = Util.getChoiceInt(3, scanner);

            switch (choice) {
                case 1: {
                    addRelation();
                    break;
                }
                case 2: {
                    addPreference();
                }
            }
        } while (choice != 3 || !checkPreference(pirates, listOfLoot));

        for (Pirate p : pirates) {
            System.out.println(p);
        }

        System.out.println("Pirate preferences and relations have saved.");
    }

    /**
     * Find a solution :
     * 1. Every pirate have their first preference if not the second
     * 2. The lowest possible cost ( Une solution naı̈ve )
     */
    private void findNaiveSolutionWithInteraction() {
//        int numberLoot;
        // Give the loot according to the first pirate, the first loot (solution naive)
        System.out.println("Solution Naïve --------------------------->");
        algoNaive();
        // Displays what the pirates have as loot
        for (Pirate p : pirates) {
            System.out.println("The pirate " + p.getName() + " has the loot : " + p.getObjectObtained() + "\n");
        }
    }

    /**
     * 1. Exchange the loot : Ask the two pirates and change their loot -
     * 2. Display the cost : The number of the jealous pirates          -
     * After each action, display the relation
     */
    private void exchangeLootAndDisplayWithInteraction() {
        int option;
        do {
            // Menu
            System.out.println("Choose your next action: ");
            System.out.println("1) echanger 2 loots;");
            System.out.println("2) Afficher le coût");
            System.out.println("3) fin");
            option = Util.getChoiceInt(3, scanner);
            switch (option) {
                case 1: {
                    exchangeLoot();
                    break;
                }

                case 2: {/* Stan
                          Number of jealous pirates
                          */
                    // Displays the number of jealous pirates
                    System.out.println("The number of jealous pirates is : " + Util.calculateCost(pirates));
                    break;
                }
            }
            // Displays the loot that every pirate has
            for (Pirate p : pirates) {
                System.out.println("The pirate " + p.getName() + " has the loot : " + p.getObjectObtained() + "\n");
            }
        } while (option != 3);
        System.out.println("END");
    }

    /**
     * Add the dislike relation between pirates by interacting with users
     */
    private void addRelation() {
        // Asks the user which pirates hate each other
        System.out.println("Le pirate _ ne s’aime pas le pirate _ ");
        System.out.println("Please fill in the characters corresponding to the pirates");
        System.out.println("The first character is : ");
        char firstName = Util.getChoiceChar(maxCharAllowed, scanner);
        System.out.println("The second character is : ");
        char secondName = Util.getChoiceChar(maxCharAllowed, scanner, firstName);

        // Puts the pirate A in the pirate B's enemies list ( same for the A )
        getPirate(firstName).addPirateDislike(getPirate(secondName));

        System.out.print(getPirate(firstName).getName() + " dislikes ");
        getPirate(firstName).printDislike();
    }

    /**
     * Add the preference between pirates by interacting with users
     */
    private void addPreference() {
        // Displays the format that user has to type, if it's not the case we ask the user to type again with the good format
        System.out.print("Please enter the information in the following format : \nA ");
        for (int i = 1; i <= numberPirates; i++) {
            System.out.print(i + " ");
        }
        System.out.println("\n(Veillez à bien séparer les informations par (au moins un) espace)");
        String inputText = Util.getInputPreference(maxCharAllowed, scanner);
        Pirate pirateChosen = getPirate(Character.toUpperCase(inputText.charAt(0)));
        ArrayList<Loot> listPreferences = new ArrayList<>();
        for (int i = 1; i <= numberPirates; i++) {
            // '2' -> 2
            int numberObject = Integer.parseInt(String.valueOf(Character.toUpperCase(inputText.charAt(i * 2))));
            listPreferences.add(new Loot(numberObject));
        }
        // here we set the new list preferences
        pirateChosen.setPreferenceList(listPreferences);
        System.out.println(pirateChosen);
    }

    /**
     * Exchange the loot between pirates by interacting with users
     */
    private void exchangeLoot() {
        // Ask the user which pirates exchange their loot
        System.out.println("Please fill in the characters corresponding to the pirates");
        System.out.println("The first character is : ");
        char firstName = Util.getChoiceChar(maxCharAllowed, scanner);
        System.out.println("The second character is : ");
        char secondName = Util.getChoiceChar(maxCharAllowed, scanner, firstName);

        // Exchange the loot
        exchangeLootWithPirateName(firstName, secondName);
    }

    /**
     * Because we use number to mark pirate instead of character, so we need another method exchangeLoot in number
     *
     * @see Controller#exchangeLoot()
     */
    private void exchangeLootInNumber() {
        System.out.println("Please fill in the numbers corresponding to the pirates for the loot exchange");
        System.out.println("The first number is : ");
        int firstName = Util.getChoiceInt(numberPirates, scanner);
        System.out.println("The second number is : ");
        int secondName = Util.getChoiceInt(numberPirates, scanner, firstName);

        // Exchange the loot
        exchangeLootWithPirateName(Util.intToChar(firstName), Util.intToChar(secondName));
    }

    /**
     * Exchange the loot between pirates
     *
     * @param firstName  first pirate name
     * @param secondName second pirate name
     */
    public void exchangeLootWithPirateName(char firstName, char secondName) {
        Loot l1 = getPirate(firstName).getObjectObtained();
        Loot l2 = getPirate(secondName).getObjectObtained();

        getPirate(firstName).setObjectObtained(l2);
        getPirate(secondName).setObjectObtained(l1);
    }

    /**
     * Get the pirate according to the name
     *
     * @param name pirate name
     * @return pirate
     */
    public Pirate getPirate(char name) {
        for (Pirate p : pirates) {
            if (p.getName() == name) {
                return p;
            }
        }
        // Because of the regular expression restriction input
        // this will theoretically never execute
        try {
            throw new Exception("Pirate " + name + " not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pirate();
    }

    /**
     * Distribution the loot
     */
    public void algoNaive() {
        int numberLoot;
        for (Pirate p : pirates) {
            int index = 0;
            // Check if the pirate have the loot
            while (p.getObjectObtained() == null) {
                numberLoot = p.getPreferenceList().get(index).getNumber();

                // If loot "numberLoot" is available then we give it to this pirate
                if (!listOfLoot.get(numberLoot - 1).isToken()) {
                    p.setObjectObtained(listOfLoot.get(numberLoot - 1));
                    listOfLoot.get(numberLoot - 1).setToken(true);
                }
                index++;
            }
        }
    }
}
