package controller;

import model.Pirate;
import model.Util;
import java.util.ArrayList;

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
        System.out.println("Welcome to Loot sharing !");
        System.out.print("For the number of pirates, ");
        int numberPirates = Util.getChoiceInt(MINPIRATES, MAXPIRATES);
        System.out.println("Number of pirates saved\n");
        init(numberPirates);
        System.out.println();

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

                    System.out.println(getPirate(firstName).getName() + " dislikes " + getPirate(firstName).getPirateDislike().get(0).getName());
                    break;
                }

                case 2: {
                    System.out.println("Please enter the information in the following format : \n A 1 2 3 " +
                            "\n(Veillez à bien séparer les informations par (au moins un) espace)");

                }
            }
        } while(choice != 3);

    }

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
