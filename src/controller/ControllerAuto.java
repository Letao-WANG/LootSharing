package controller;

import model.Loot;
import model.Pirate;
import model.Util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Responsible for storing and manipulating Pirates data, the program mainly runs in this Class for PART II
 */

public class ControllerAuto {
    /**
     * Scanner class for interaction with user
     */
    private final Scanner scanner;

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
     * Constructor of Scanner
     */
    public ControllerAuto() {
        scanner = new Scanner(System.in);
    }

    /**
     * Main method of sujet part II
     */
    public void runWithAutomation() {
        // initializer the data to java class, get the pirates without distributing loot
        readData();
        // distribute loots to pirates
        algoNaive();
        // enter the menu
        menuResolution();
    }

    /**
     * Read data from file "info.data", convert and save to Java Class
     */
    public void readData() {
        File file = new File("src/data/equipage3");
//        File file = new File("src/data/equipage2");
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
    public void menuResolution() {
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
            choice = Util.getChoiceInt(4, scanner);

            switch (choice) {
                case 1: {
                    algoOptimal(0);
//                    algoApproximation(1000);
//                    randomAlgo(pirates, 0);
                    break;
                }
                case 2: {
                    exchangeLootInNumber();
                    System.out.println(Util.calculateCost(pirates));
                    break;
                }
                case 3: {
                    saveData();
                    break;
                }
            }
        } while (choice != 4);
        scanner.close();
    }

    /**
     * Algorithme 1 : Un algorithme d’approximation (naïf) in the sujet
     * exchange loot random to optimiser the cost
     *
     * Easy to obtain the local optimal solution, but not necessarily the global optimal solution
     *
     * @param k the number of loop
     */
    public void algoApproximation(int k) {
        int costOld = Util.calculateCost(pirates);
        for(int index = 0; index<k; index++){
            int firstNumber = Util.randomInt(numberPirates - 1, 0);
            Pirate firstPirate = getPirate(Util.intToChar(firstNumber));
            Pirate secondPirate = firstPirate.getPirateDislike().get(Util.randomInt(firstPirate.getNumberDislike() - 1, 0));
            exchangeLootWithPirateName(firstPirate.getName(), secondPirate.getName());

            // roll back
            if (Util.calculateCost(pirates) > costOld) {
                exchangeLootWithPirateName(secondPirate.getName(), firstPirate.getName());
            }
        }
    }

    /**
     * Algorithme 2, algo optimal
     *
     * The biggest problem of algorithme d'approximation is that it is easy to obtain a local optimal solution
     * So we add a probability function <strong>"sigmoid"</strong> that appropriately <strong>allows the cost value to increase,
     * so that the algorithm is no longer obsessed with the local optimal solution.</strong>
     *
     * Le plus gros problème de l'algorithme d'approximation est qu'il est facile d'obtenir une solution localement optimale
     * Nous ajoutons donc une fonction de probabilité "sigmoid" qui permet à la valeur du coût d'augmenter de manière appropriée
     * Afin que l'algorithme ne soit pas obsédé par une solution localement optimale.
     *
     * @param cost the most idea cost (number of jealous pirate)
     *
     */
    public void algoOptimal(int cost) {
        int countLimit = 1000000;
        int costOld = Util.calculateCost(pirates);
        int count = 0;
        while(Util.calculateCost(pirates) != cost){
            if(count == countLimit){
                break;
            }
            int firstNumber = Util.randomInt(numberPirates - 1, 0);
            Pirate firstPirate = getPirate(Util.intToChar(firstNumber));
            Pirate secondPirate = firstPirate.getPirateDislike().get(Util.randomInt(firstPirate.getNumberDislike() - 1, 0));
            exchangeLootWithPirateName(firstPirate.getName(), secondPirate.getName());

            // roll back
            int diff = Util.calculateCost(pirates) - costOld;
            if (valueFunction(diff)) {
                exchangeLootWithPirateName(secondPirate.getName(), firstPirate.getName());
            }
            count++;
        }
        if(count == countLimit){
            algoOptimal(cost+1);
        }
    }

    /**
     * Converting probabilities to Boolean variables
     * @param diff difference of two cost (new cost and old cost)
     * @return if we roll back
     *
     * @see ControllerAuto#algoOptimal
     */
    public boolean valueFunction(int diff){
        double possibility = sigmoid(diff);
        double possRandom = Math.random();
        return possibility > possRandom;
    }

    /**
     * Sigmoid function, used as the activation function of artificial neurons
     * Here we use it to replace the difference with a probability value from 0 to 1, and modifier to -2*z
     * @param z difference of two cost (new cost and old cost)
     * @return probability value from 0 to 1
     *
     * @see ControllerAuto #algoOptimal
     */
    public double sigmoid(double z){
        return 1/(1+Math.exp(-2*z));
    }

    /**
     * Algorithme 2 algo random
     *
     *
     * @param pirates list of pirate
     * @param minCost idea cost
     */
    public void randomAlgo(ArrayList<Pirate> pirates, int minCost) {
        System.out.println("Trying to find the solution with cost " + minCost + ", please wait...");
        int count = 0;
        int countLimit = 10000000;
        while(Util.calculateCost(pirates) != minCost){
            if(count>countLimit){
                break;
            }
            int firstPirate = Util.randomInt(pirates.size() - 1, 0);
            Pirate p1 = pirates.get(firstPirate);
            Pirate p2 = p1.getPirateDislike().get(Util.randomInt(p1.getNumberDislike() - 1, 0));

            Loot l1 = p1.getObjectObtained();
            Loot l2 = p2.getObjectObtained();

            p1.setObjectObtained(l2);
            p2.setObjectObtained(l1);
            count++;
        }
        if(count>countLimit){
            System.out.println("It's impossible to find the solution with cost " + minCost);
            randomAlgo(pirates, minCost+1);
        } else {
            System.out.println("Success to find the solution with count : " + count);
        }
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

    /**
     * Display the information of pirates
     */
    public void printPirates() {
        for (Pirate p : pirates) {
            System.out.println(p);
        }
    }

    /**
     * Display the information of loots
     */
    public void printLoots() {
        for (Pirate p : pirates) {
            System.out.println("The pirate " + p.getName() + " has the loot : " + p.getObjectObtained() + "\n");
        }
    }

    /**
     * Display the information of cost
     */
    public void printCost() {
        System.out.println("The number of jealous pirates is : " + Util.calculateCost(pirates) + "\n");
    }

    /**
     * Save the data into file
     * <p>
     * Stan
     */
    public void saveData() {
        System.out.print("Entrez le nom du fichier où enregistrer le résultat : ");
        String fileName = scanner.nextLine();
        File file = new File("src/data/"+fileName);
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write("Cout:"+Util.calculateCost(pirates)+"\n");
            for (Pirate p : pirates) {
                fileWriter.write("Pirate " + p.getName() + " : " + "Loot " + p.getObjectObtained() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Because we use number to mark pirate instead of character, so we need another method exchangeLoot in number
     *
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


}
