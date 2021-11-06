package controller;

import model.Loot;
import model.Pirate;
import model.Util;

import java.util.ArrayList;

/**
 * Responsible for storing and manipulating Pirates data, the program mainly runs in this Class
 */

public class Controller {
    private static final int MAXPIRATES = 26;
    private ArrayList<Pirate> pirates;

    public Controller() {
    }

    private void init(int numPirates) {
        pirates = new ArrayList<>();
        for (int i = 0; i < numPirates; i++) {
            char name = (char) ('A' + i);
            pirates.add(new Pirate(name));
        }
        for (Pirate p : pirates) {
            System.out.println("Pirate " + p.getName());
        }
    }

    /**
     * Execute the program of the project in the terminal
     */
    public void runWithoutGraphic() {

        /*------------------------- Part I ---------------------------------
        - Letao                                                            -
        - Config a pirate crew with :  n pirates (designated as A,B,C ...) -
        - n loots ( designated as a number) TO BE TESTED with 26 pirates   -
        ------------------------------------------------------------------*/

        System.out.println("Welcome to Loot sharing !");
        System.out.println("Please enter the number of pirates: ");
        int numberPirates = Util.getChoiceInt(MAXPIRATES);
        char maxCharAllowed = (char) ('A' + numberPirates - 1);
        System.out.println();
        init(numberPirates);
        System.out.println(numberPirates + " Pirates have been initialized.");


        // Initialisation of the loot list
        ArrayList<Loot> listOfLoot = new ArrayList<>();
        for (int i = 1; i < numberPirates + 1; i++) {
            Loot l = new Loot(i);
            listOfLoot.add(l);
        }
        System.out.println(numberPirates + " Loots have been initialized \n");

        //Default preference list for the pirates

        for (Pirate p : pirates) {
            p.setPreferenceList(listOfLoot);
        }


        /*--------------------------Part II ----------------------------
        - Letao                                                        -
        - Menu with 2 options : Add a relation & Add a preference      -
        - Check if all the pirates have their preferences              -
        ------------------------------------------------------------- */

        int choice;
        do {
            // Menu
            System.out.println("Choose your next action: ");
            System.out.println("1) ajouter une relation;");
            System.out.println("2) ajouter des préférences;");
            System.out.println("3) fin");
            choice = Util.getChoiceInt(3);

            switch (choice) {
                case 1: {
                    // Asks the user which pirates hate each other
                    System.out.println("Le pirate _ ne s’aime pas le pirate _ ");
                    System.out.println("Please fill in the characters corresponding to the pirates");
                    System.out.println("The first character is : ");
                    char firstName = Util.getChoiceChar(maxCharAllowed);
                    System.out.println("The second character is : ");
                    char secondName = Util.getChoiceChar(maxCharAllowed, firstName);

                    // Puts the pirate A in the pirate B's enemies list ( same for the A )
                    getPirate(firstName).addPirateDislike(getPirate(secondName));

                    System.out.print(getPirate(firstName).getName() + " dislikes ");
                    getPirate(firstName).printDislike();
                    break;
                }

                case 2: {
                    // Displays the format that user has to type, if it's not the case we ask the user to type again with the good format
                    System.out.print("Please enter the information in the following format : \nA ");
                    for (int i = 1; i <= numberPirates; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println("\n(Veillez à bien séparer les informations par (au moins un) espace)");
                    String inputText = Util.getInputPreference(maxCharAllowed);
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
            }
        } while (choice != 3);

        for (Pirate p : pirates) {
            System.out.println(p + "\n");
        }

        System.out.println("Pirate preferences and relations have saved.");



        /*------------------------- Part III ----------------------------
        - Paul                                                          -
        - III. Find a solution :                                        -
        - 1. Every pirate have their first preference if not the second -
        - 2. The lowest possible cost ( Une solution naı̈ve )            -
        -------------------------------------------------------------- */
        int numberLoot;
        // Give the loot according to the first pirate, the first loot (solution naive)
        System.out.println("Solution Naïve --------------------------->");
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
        // Displays what the pirates have as loot
        for (Pirate p : pirates) {
            System.out.println("The pirate " + p.getName() + " has the loot : " + p.getObjectObtained() + "\n");
        }



        /* ------------------------ Part IV --------------------------------
        - Stan                                                             -
        - 1. Exchange the loot : Ask the two pirates and change their loot -
        - 2. Display the cost : The number of the jealous pirates          -
        - After each action, display the relation                          -
        ----------------------------------------------------------------- */

        /* Stan
        Exchange the loot : Ask the two pirates and change their loot
         */

        int option;
        do {
            // Menu
            System.out.println("Choose your next action: ");
            System.out.println("1) echanger 2 loots;");
            System.out.println("2) Afficher le coût");
            System.out.println("3) fin");
            option = Util.getChoiceInt(3);
            switch (option) {
                case 1: {
                    // Ask the user which pirates exchange their loot
                    System.out.println("Please fill in the characters corresponding to the pirates");
                    System.out.println("The first character is : ");
                    char firstName = Util.getChoiceChar(maxCharAllowed);
                    System.out.println("The second character is : ");
                    char secondName = Util.getChoiceChar(maxCharAllowed, firstName);

                    // Exchange the loot
                    Loot l1 = getPirate(firstName).getObjectObtained();
                    Loot l2 = getPirate(secondName).getObjectObtained();

                    getPirate(firstName).setObjectObtained(l2);
                    getPirate(secondName).setObjectObtained(l1);

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

    public Pirate getPirate(char name) {
        for (Pirate p : pirates) {
            if (p.getName() == name) {
                return p;
            }
        }
        return null;
    }
}
