package controller;

import model.Pirate;

import java.util.ArrayList;
import java.util.Scanner;

public class Controller {
    private static final int MINPIRATES = 0;
    private static final int MAXPIRATES = 100;

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
        System.out.println("Please enter the number of pirates : (" + MINPIRATES + " - " + MAXPIRATES + ")");
        int numberPirates = getChoiceInt(MINPIRATES, MAXPIRATES);
        System.out.println("Number of pirates saved\n");
        init(numberPirates);
        System.out.println();

        int choice;
        do {
            System.out.println("Choose your next action : ");
            System.out.println("1) ajouter une relation;");
            System.out.println("2) ajouter des préférences;");
            System.out.println("3) fin.");
            choice = getChoiceInt(1, 3);
            switch (choice) {
                case 1: {
                    System.out.println("Le pirate _ ne s’aime pas le pirate _ ");
                    System.out.println("Please fill in the characters corresponding to the pirates");
                    System.out.println("The first character is : ");
                    char maxChar = (char)('A' + numberPirates);
                    char firstName = getChoiceChar( maxChar );
                    System.out.println("The second character is : ");
                    char secondName = getChoiceChar(maxChar, firstName);

                    getPirate(firstName).addPirateDislike(getPirate(secondName));

                    System.out.println(getPirate(firstName).getName() + " dislikes " + getPirate(firstName).getPirateDislike().get(0).getName());
                    break;
                }
            }
        } while(choice != 3);

    }

    /**
     * Utilisé pour obtenir le numéro entré par l'utilisateur
     * <p>
     * Used to get the number entered by the user
     *
     * @param min       Minimum allowed number
     * @param max       Maximum number allowed
     * @param exception Number that is not allowed
     * @return
     */
    public int getChoiceInt(int min, int max, int exception) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        while (!(number >= min && number <= max && number != exception)) {
            System.out.println("Please input the appropriate number : (" + min + " - " + max + ") or verifier check if an illegal number is entered");
            number = sc.nextInt();
        }
        return number;
    }

    /**
     * @see Controller#getChoiceInt(int, int, int)
     */
    public int getChoiceInt(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        while (!(number >= min && number <= max)) {
            System.out.println("Please input the appropriate number : (" + min + " - " + max + ")");
            number = sc.nextInt();
        }
        return number;
    }

    public char getChoiceChar( char max) {
        Scanner sc = new Scanner(System.in);
        char c = Character.toUpperCase(sc.next().charAt(0));
        while (!(c >= 'A' && c <= 'Z')) {
            System.out.println("Please input the appropriate character : ( A - " +  max + " )");
            c = Character.toUpperCase(sc.next().charAt(0));
        }
        return c;
    }

    public char getChoiceChar( char max, char exception) {
        Scanner sc = new Scanner(System.in);
        char c = Character.toUpperCase(sc.next().charAt(0));
        while (!(c >= 'A' && c <= 'Z' && c != exception)) {
            System.out.println("Please input the appropriate character : ( A - " +  max + "or verifier check if an illegal character is entered");
            c = Character.toUpperCase(sc.next().charAt(0));
        }
        return c;
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
