package execute;

import controller.Controller;

import java.util.Scanner;

import controller.ControllerAuto;
import model.Util;

/**
 * Classe d'exécution lorsqu'il n'y a pas d'interface graphique
 * <p>
 * Execution class when there is no graphical interface
 */
public class Execute {
    public static void main(String[] args) {
        ExecuteSelect.execute();
    }
}
