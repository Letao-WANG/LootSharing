package execute;

import controller.Controller;
import controller.ControllerAuto;
import model.Util;

import java.util.Scanner;

/**
 * Class to execute the menu of selecting part I or II
 * But it is not main class, only provide static method execute()
 */
public class ExecuteSelect {

    /**
     * The name of data file
     */
    private static final String data = "equipage1";

    /**
     * Execute the program with a menu that allows the user to choice between the project PART I or PART II
     */
    public static void execute() {
        Scanner sc = new Scanner(System.in);
        Controller controller = new Controller(sc);
        ControllerAuto cAuto = new ControllerAuto(sc);
        int choix;
        do {
            System.out.println("------MENU------");
            System.out.println("1 - Projet Part I");
            System.out.println("2 - Projet Part II");
            System.out.println("3 - FIN");
            choix = Util.getChoiceInt(3, sc);
            switch (choix) {
                case 1:
                    controller.runWithUserInteraction();
                    break;
                case 2:
                    cAuto.runWithAutomation(data);
                    break;
                case 3:
                    System.out.println("EXIT PROGRAM .....");
                    break;
                default:
                    System.out.println("Choix incorrect");
            }
        } while (choix != 3);
        sc.close();
    }
}
