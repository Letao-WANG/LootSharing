package execute;

import controller.Controller;
import controller.ControllerAuto;

/**
 * Classe d'exécution lorsqu'il n'y a pas d'interface graphique
 *
 * Execution class when there is no graphical interface
 */
public class Execute {
    public static void main(String[] args) {
        // Sujet Part I
//        Controller controller = new Controller();
//        controller.runWithUserInteraction();

        // Sujet Part II
        ControllerAuto controller = new ControllerAuto();
        controller.runWithAutomation();
    }
}
