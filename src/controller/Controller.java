package controller;

import model.Object;
import model.Pirate;
import model.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    public void runWithoutGraphic() {

        /*------------------------- Part I ---------------------------------
        - Letao                                                            -
        - Config a pirate crew with :  n pirates (designated as A,B,C ...) -
        - n loots ( designated as a number) TO BE TESTED with 26 pirates   -
        ------------------------------------------------------------------*/

        System.out.println("Welcome to Loot sharing !");
        System.out.print("Please enter the number of pirates: ");
        int numberPirates = Util.getChoiceInt(MAXPIRATES);
        char maxCharAllowed = (char) ('A' + numberPirates - 1);
        System.out.println();
        init(numberPirates);
        System.out.println( numberPirates + " Pirates have been initialized.\n");

        /*--------------------------Part II ----------------------------
        - Letao                                                        -
        - Menu with 2 options : Add a relation & Add a preference      -
        - Check if all the pirates have their preferences              -
        ------------------------------------------------------------- */

        int choice;
        do {
            System.out.println("Choose your next action: ");
            System.out.println("1) ajouter une relation;");
            System.out.println("2) ajouter des préférences;");
            System.out.println("3) fin");
            choice = Util.getChoiceInt(3);
            // choice can only be 1, 2, or 3, so we needn't case 3 or default in switch structure
            switch (choice) {
                case 1: {
                    System.out.println("Le pirate _ ne s’aime pas le pirate _ ");
                    System.out.println("Please fill in the characters corresponding to the pirates");
                    System.out.println("The first character is : ");
                    char firstName = Util.getChoiceChar(maxCharAllowed);
                    System.out.println("The second character is : ");
                    char secondName = Util.getChoiceChar(maxCharAllowed, firstName);

                    getPirate(firstName).addPirateDislike(getPirate(secondName));

                    System.out.print(getPirate(firstName).getName() + " dislikes ");
                    getPirate(firstName).printDislike();
                    break;
                }

                case 2: {
                    System.out.print("Please enter the information in the following format : \nA ");
                    for (int i = 1; i <= numberPirates; i++) {
                        System.out.print(i + " ");
                    }
                    System.out.println("\n(Veillez à bien séparer les informations par (au moins un) espace)");
                    String inputText = Util.getInputPreference(maxCharAllowed);
                    Pirate pirateChosen = getPirate(Character.toUpperCase(inputText.charAt(0)));
                    ArrayList<Object> listPreferences = new ArrayList<>();
                    for (int i = 1; i <= numberPirates; i++) {
                        // '2' -> 2
                        int numberObject = Integer.parseInt(String.valueOf(Character.toUpperCase(inputText.charAt(i*2))));
                        listPreferences.add(new Object(numberObject));
                    }
                    // here we set the new list preferences
                    pirateChosen.setPreferenceList(listPreferences);
                    System.out.println(pirateChosen);
                }
            }
        } while (choice != 3);

        for(Pirate p : pirates){
            System.out.println(p + "\n");
        }

        System.out.println("Pirate preferences and relations have saved.");


        /*------------------------- Part III ----------------------------
        - Paul                                                          -
        - III. Find a solution :                                        -
        - 1. Every pirate have their first preference if not the second -
        - 2. The lowest possible cost ( Une solution naı̈ve )            -
        -------------------------------------------------------------- */

        // TO DO ...


        /* ------------------------ Part IV --------------------------------
        - Stan                                                             -
        - 1. Exchange the loot : Ask the two pirates and change their loot -
        - 2. Display the cost : The number of the jealous pirates          -
        - After each action, display the relation                          -
        ----------------------------------------------------------------- */

        // TO DO ...

    }

    public ArrayList<Pirate> getPirates() {
        return pirates;
    }

    public void setPirates(ArrayList<Pirate> pirates) {
        this.pirates = pirates;
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
