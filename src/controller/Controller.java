package controller;

import model.Pirate;

import java.util.ArrayList;

public class Controller {
    private ArrayList<Pirate> pirates;

    public Controller(ArrayList<Pirate> pirates) {
        this.pirates = pirates;
    }

    public Controller(){

    }

    public ArrayList<Pirate> getPirates() {
        return pirates;
    }

    public void setPirates(ArrayList<Pirate> pirates) {
        this.pirates = pirates;
    }
}
