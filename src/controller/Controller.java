package controller;

import jdk.swing.interop.SwingInterOpUtils;
import model.Loot;
import model.Pirate;
import model.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Responsible for storing and manipulating Pirates data, the program mainly runs in this Class
 */

public class Controller {
    private static final int MAXPIRATES = 26;
    private ArrayList<Pirate> pirates;
    private ArrayList<Loot> listOfLoot;

    private int numberPirates;
    private char maxCharAllowed;

    public Controller() {
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
        initialization();

        /*--------------------------Part II ----------------------------
        - Letao                                                        -
        - Menu with 2 options : Add a relation & Add a preference      -
        - Check if all the pirates have their preferences              -
        ------------------------------------------------------------- */
        addPreferenceAndRelation();

        /*------------------------- Part III ----------------------------
        - Paul                                                          -
        - III. Find a solution :                                        -
        - 1. Every pirate have their first preference if not the second -
        - 2. The lowest possible cost ( Une solution naı̈ve )            -
        -------------------------------------------------------------- */
        findNaiveSolution();

        /* ------------------------ Part IV --------------------------------
        - Stan                                                             -
        - 1. Exchange the loot : Ask the two pirates and change their loot -
        - 2. Display the cost : The number of the jealous pirates          -
        - After each action, display the relation                          -
        ----------------------------------------------------------------- */
        exchangeLootAndDisplay();
    }

    /**
     * Config a pirate crew with :  n pirates (designated as A,B,C ...)
     * n loots ( designated as a number) TO BE TESTED with 26 pirates
     */
    private void initialization() {
        System.out.println("Welcome to Loot sharing !");
        System.out.println("Please enter the number of pirates: ");
        numberPirates = Util.getChoiceInt(MAXPIRATES);
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

        //Default preference list for the pirates
        for (Pirate p : pirates) {
            p.setPreferenceList(listOfLoot);
        }
    }

    /**
     * Menu with 2 options : Add a relation & Add a preference      -
     * Check if all the pirates have their preferences
     */
    private void addPreferenceAndRelation() {
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
                    addRelation();
                    break;
                }
                case 2: {
                    addPreference();
                }
            }
        } while (choice != 3);

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
    private void findNaiveSolution() {
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
    private void exchangeLootAndDisplay() {
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
        char firstName = Util.getChoiceChar(maxCharAllowed);
        System.out.println("The second character is : ");
        char secondName = Util.getChoiceChar(maxCharAllowed, firstName);

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

    /**
     * Exchange the loot between pirates by interacting with users
     */
    private void exchangeLoot() {
        // Ask the user which pirates exchange their loot
        System.out.println("Please fill in the characters corresponding to the pirates");
        System.out.println("The first character is : ");
        char firstName = Util.getChoiceChar(maxCharAllowed);
        System.out.println("The second character is : ");
        char secondName = Util.getChoiceChar(maxCharAllowed, firstName);

        // Exchange the loot
        exchangeLootWithPirateName(firstName, secondName);
    }

    private void exchangeLootInNumber(){
        System.out.println("Please fill in the numbers corresponding to the pirates");
        System.out.println("The first number is : ");
        int firstName = Util.getChoiceInt(numberPirates);
        System.out.println("The second number is : ");
        int secondName = Util.getChoiceInt(numberPirates, firstName);

        // Exchange the loot
        exchangeLootWithPirateName(Util.intToChar(firstName), Util.intToChar(secondName));
    }

    /**
     * Exchange the loot between pirates
     * @param firstName first pirate name
     * @param secondName second pirate name
     */
    public void exchangeLootWithPirateName(char firstName, char secondName){
        Loot l1 = getPirate(firstName).getObjectObtained();
        Loot l2 = getPirate(secondName).getObjectObtained();

        getPirate(firstName).setObjectObtained(l2);
        getPirate(secondName).setObjectObtained(l1);
    }

    /**
     * Get the pirate according to the name
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
        try{
            throw new Exception("Pirate not found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Pirate();
    }

    /*-------------------------------------------------------------*/
    /*---------------------- Sujet partie II ----------------------*/
    /*-------------------------------------------------------------*/

    /**
     * Main method of sujet part II
     */
    public void runWithAutomation(){
        readData();
        algoNaive();
        menu();
    }

    /**
     * Read data from file "info.data", convert and save to Java Class
     */
    public void readData() {
        File file = new File("src/data/info.data");

        // initialisation
        pirates = new ArrayList<>();
        listOfLoot = new ArrayList<>();

        // read file and transform to Class Java
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                ArrayList<Integer> listName = Util.getNameFromLine(line);
                switch (Util.getActionFromLine(line)) {
                    case "pirate": {
                        char namePirate = Util.intToChar(listName.get(0));
                        pirates.add(new Pirate(namePirate));
                        break;
                    }
                    case "objet": {
                        int number = listName.get(0);
                        listOfLoot.add(new Loot(number));
                        break;
                    }
                    case "deteste": {
                        char namePirate1 = Util.intToChar(listName.get(0));
                        char namePirate2 = Util.intToChar(listName.get(1));
                        Pirate p1 = getPirate(namePirate1);
                        Pirate p2 = getPirate(namePirate2);
                        p1.addPirateDislike(p2);
                        break;
                    }
                    case "preferences": {

                        Pirate pirateChosen = getPirate(Util.intToChar(listName.get(0)));
                        ArrayList<Loot> listPreferences = new ArrayList<>();
                        for (int i = 1; i < listName.size(); i++) {
                            listPreferences.add(new Loot(listName.get(i)));
                        }
                        // here we set the new list preferences
                        pirateChosen.setPreferenceList(listPreferences);
                        break;
                    }
                }
            }
            numberPirates = pirates.size();
            sc.close();
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Enter the menu with user interaction
     */
    public void menu(){
        int choice;
        do {
            printPirates();
            printCost();
            // Menu
            System.out.println("Choose your next action: ");
            System.out.println("1) résolution automatique ;");
            System.out.println("2) résolution manuelle ;");
            System.out.println("3) sauvegarde ;");
            System.out.println("4) fin");
            choice = Util.getChoiceInt(4);

            switch (choice) {
                case 1: {
                    approximate(50);
                    break;
                }
                case 2: {
                    exchangeLootInNumber();
                    System.out.println(Util.calculateCost(pirates));
                }
                case 3: {
                    saveData();
                }
            }
        } while (choice != 4);
    }

    /**
     * Algorithme 1 : Un algorithme d’approximation (naïf) in the sujet
     * exchange loot random to optimiser the cost
     * @param k number of loop
     */
    public void approximate(int k){
        int costOld = Util.calculateCost(pirates);
        for(int i=0; i<k; i++){
            int firstPirate = Util.randomInt(numberPirates, 1 );
            int secondPirate = Util.randomInt(numberPirates, 1, firstPirate);
            exchangeLootWithPirateName(Util.intToChar(firstPirate), Util.intToChar(secondPirate));

            // roll back
            if(Util.calculateCost(pirates) >= costOld){
                exchangeLootWithPirateName(Util.intToChar(secondPirate), Util.intToChar(firstPirate));
            }
        }
    }

    public void optimise(){

    }

    /**
     * Distribution the loot
     */
    public void algoNaive(){
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

    /**
     * Display the information of pirates
     */
    public void printPirates(){
        for (Pirate p : pirates) {
            System.out.println(p);
        }
    }

    /**
     * Display the information of loots
     */
    public void printLoots(){
        for (Pirate p : pirates) {
            System.out.println("The pirate " + p.getName() + " has the loot : " + p.getObjectObtained() + "\n");
        }
    }

    /**
     * Display the information of cost
     */
    public void printCost(){
        System.out.println("The number of jealous pirates is : " + Util.calculateCost(pirates) + "\n");
    }

    /**
     * Add loots to the pirates, use in testing
     */
    public void addLoot(){
        int[] numbers = {1, 3, 2, 4};
        int ind = 0;
        for (Pirate p : pirates){
            p.setObjectObtained(new Loot(numbers[ind]));
            ind++;
        }
    }

    /**
     * Save the data into file
     */
    public void saveData(){

    }
}
