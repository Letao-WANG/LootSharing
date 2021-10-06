package execute;

import controller.Controller;

import java.util.Scanner;

/**
 * Classe d'exécution lorsqu'il n'y a pas d'interface graphique
 *
 * Execution class when there is no graphical interface
 */
public class ExecuteWithoutGraphic {

    private void run(){
        Scanner sc = new Scanner(System.in);
        int numberPirates = sc.nextInt();
        while ( !(numberPirates > 0 && numberPirates < 100) ){
            System.out.println("Please input the appropriate number : ( 0-100 )");
            numberPirates = sc.nextInt();
        }
        System.out.println("Number of pirates saved");

        System.out.println("Choose your next action : ");
        System.out.println("1) ajouter une relation;");
        System.out.println("2) fin.");


    }

    private int getChoice(int min, int max){
        Scanner sc = new Scanner(System.in);
        int number = sc.nextInt();
        while ( !(number > min && number < max) ){
            System.out.println("Please input the appropriate number : ( " + min + "- "+ max +" )");
            number = sc.nextInt();
        }
        return number;
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
    }
}
