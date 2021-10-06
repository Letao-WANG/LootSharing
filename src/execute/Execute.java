package execute;

import controller.Controller;

/**
 * Classe d'exécution lorsqu'il n'y a pas d'interface graphique
 *
 * Execution class when there is no graphical interface
 */
public class Execute {
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.runWithoutGraphic();
    }
}
