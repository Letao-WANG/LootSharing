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
        if(pirateDislike != null && autre != null && (!pirateDislike.contains(autre))){
            pirateDislike.add(autre);
            autre.addOtherPirateDislike(this);
        }
    }

    private void addOtherPirateDislike(Pirate autre){
        if(pirateDislike != null && autre != null){
            pirateDislike.add(autre);
        }
    }

    public void printDislike(){
        ArrayList<Pirate> list = getPirateDislike();
        for(Pirate p : list){
            System.out.print(p.getName() + " ");
        }
        System.out.println();
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

    public void printPreferenceList() {
        ArrayList<Object> list = getPreferenceList();
        for(Object o : list){
            System.out.println(o.getNumber() + " ");
        }
    }

    public void setPreferenceList(ArrayList<Object> preferenceList) {
        this.preferenceList = preferenceList;
    }

    public String toString() {
        String res = "Pirate " + getName() + ": ";
        for(Object o : preferenceList){
            res += o.getNumber() + ", ";
        }
        res += "\n";
        if(pirateDislike.size() != 0){
            res += "Pirate " + getName() + " dislike ";
            for(Pirate p : pirateDislike){
                res += p.getName() + ", ";
            }
        }
        return res;

    }
}
