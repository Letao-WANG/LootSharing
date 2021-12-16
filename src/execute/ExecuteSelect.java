package execute;

import controller.Controller;
import controller.ControllerAuto;

import java.util.Scanner;

public class ExecuteSelect {
    /**
     * Execute the program with a menu that allows the user to choice between the project PART I or PART II
     */
    public static void execute(){
        Controller controller = new Controller();
        ControllerAuto cAuto = new ControllerAuto();
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
                case 2 : cAuto.runWithAutomation();
                    break;
                case 3 : System.out.println("EXIT PROGRAM .....");
                    break;
                default: System.out.println("Choix incorrect");
            }
        }while(choix!=3);

        sc.close();
    }
}
