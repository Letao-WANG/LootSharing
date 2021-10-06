package model;

import java.util.ArrayList;

public class Pirate {
    private ArrayList<Pirate> pirateDislike;
    private Object[] preferenceList;

    public Pirate(ArrayList<Pirate> pirateDislike, Object[] preferenceList) {
        this.pirateDislike = pirateDislike;
        this.preferenceList = preferenceList;
    }

    public ArrayList<Pirate> getPirateDislike() {
        return pirateDislike;
    }

    public void setPirateDislike(ArrayList<Pirate> pirateDislike) {
        this.pirateDislike = pirateDislike;
    }

    public Object[] getPreferenceList() {
        return preferenceList;
    }

    public void setPreferenceList(Object[] preferenceList) {
        this.preferenceList = preferenceList;
    }
}
