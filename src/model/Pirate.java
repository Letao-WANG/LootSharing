package model;

import java.util.ArrayList;

public class Pirate {
    private char name;
    private ArrayList<Pirate> pirateDislike;
    private ArrayList<Object> preferenceList;
    
    public Pirate( char name ) {
        this.name = name;
        this.pirateDislike = new ArrayList<>();
        this.preferenceList = new ArrayList<>();
    }

    public ArrayList<Pirate> getPirateDislike() {
        return pirateDislike;
    }

    public void setPirateDislike(ArrayList<Pirate> pirateDislike) {
        this.pirateDislike = pirateDislike;
    }

    public void addPirateDislike(Pirate autre){
        if(pirateDislike != null && autre != null){
            pirateDislike.add(autre);
        }
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public ArrayList<Object> getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(ArrayList<Object> preferenceList) {
        this.preferenceList = preferenceList;
    }

    public String toString() {
        return "Pirate " + getName() + ". ";
    }
}
