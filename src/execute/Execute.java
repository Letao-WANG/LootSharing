package execute;

import controller.Controller;
import java.util.Scanner;
import model.Util;

/**
 * Classe d'exécution lorsqu'il n'y a pas d'interface graphique
 *
 * Execution class when there is no graphical interface
 */
public class Execute {
    public static void main(String[] args) {
        Controller controller = new Controller();
        Scanner sc = new Scanner(System.in);
        int choix = 0;
        do{
            System.out.println("------MENU------");
            System.out.println("1 - Projet Part I");
            System.out.println("2 - Projet Part II");
            System.out.println("3 - FIN");
            choix = sc.nextInt();
            switch (choix){
                case 1 : controller.runWithUserInteraction();
                break;
                case 2 : controller.runWithAutomation();
                break;
                default: System.out.println("Choix incorrect");
            }
        }while(choix!=3);
//
        sc.close();
    }
}
