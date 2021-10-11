package controller;

import model.Object;
import model.Pirate;
import model.Util;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private static final int MINPIRATES = 1;
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
        for( Pirate p : pirates){
            System.out.println(p);
        }
    }

    public void runWithoutGraphic() {

        /*------------------------- Part I ---------------------------------
        - Config a pirate crew with :  n pirates (designated as A,B,C ...) -
        - n loots ( designated as a number) TO BE TESTED with 26 pirates   -
        ------------------------------------------------------------------*/

        System.out.println("Welcome to Loot sharing !");
        System.out.print("For the number of pirates, ");
        int numberPirates = Util.getChoiceInt(MINPIRATES, MAXPIRATES);
        System.out.println("Number of pirates saved\n");
        init(numberPirates);
        System.out.println();

        /*--------------------------Part II ----------------------------
        - Menu with 2 options : Add a relation & Add a preference      -
        - Check if all the pirates have their preferences              -
        ------------------------------------------------------------- */

        int choice;
        do {
            System.out.println("Choose your next action : ");
            System.out.println("1) ajouter une relation;");
            System.out.println("2) ajouter des préférences;");
            choice = Util.getChoiceInt(1, 3);
            switch (choice) {
                case 1: {
                    System.out.println("Le pirate _ ne s’aime pas le pirate _ ");
                    System.out.println("Please fill in the characters corresponding to the pirates");
                    System.out.println("The first character is : ");
                    char maxChar = (char)('A' + numberPirates);
                    char firstName = Util.getChoiceChar( maxChar );
                    System.out.println("The second character is : ");
                    char secondName = Util.getChoiceChar(maxChar, firstName);

                    getPirate(firstName).addPirateDislike(getPirate(secondName));

//                    System.out.println(getPirate(firstName).getName() + " dislikes " + getPirate(firstName).getPirateDislike().get(0).getName());
                    break;
                }

                case 2: {
                    System.out.println("Please enter the information in the following format : \n A 1 2 3 " +
                            "\n(Veillez à bien séparer les informations par (au moins un) espace)");

                }
            }
        } while(choice != 3);


        /*------------------------- Part III ----------------------------
        - III. Find a solution :                                        -
        - 1. Every pirate have their first preference if not the second -
        - 2. The lowest possible cost ( Une solution naı̈ve )            -
        -------------------------------------------------------------- */

        // TO DO ...

    }

//    public ArrayList<Object> getPiratePreferenceList(char maxPirate, int maxObject) {
//        Scanner sc = new Scanner(System.in);
//        if(this.pirates != null){
//            String line = sc.nextLine();
//        }
//        char c = Character.toUpperCase(sc.next().charAt(0));
//        while (!(c >= 'A' && c <= 'Z')) {
//            System.out.println("Please input the appropriate character : ( A - " +  max + " )");
//            c = Character.toUpperCase(sc.next().charAt(0));
//        }
//        return c;
//    }

    public ArrayList<Pirate> getPirates() {
        return pirates;
    }

    public void setPirates(ArrayList<Pirate> pirates) {
        this.pirates = pirates;
    }

    public Pirate getPirate(char name){
        for(Pirate p : pirates){
            if(p.getName() == name){
                return p;
            }
        }
        return null;
    }
}
