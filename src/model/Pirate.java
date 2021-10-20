package model;

import java.util.ArrayList;

/**
 *  Class Pirate
 *
 *  name : name of this pirate
 *  pirateDislike : list of pirates that this pirate dislike
 *  preferenceList : list of objects that this pirate prefer
 *  objectObtained : the object that this pirate has obtained by algo or solution
 */
public class Pirate {
    private char name;
    private ArrayList<Pirate> pirateDislike;
    private ArrayList<Loot> preferenceList;
    private Loot lootObtained = null;

    /**
     * Create an object of the class Pirate
     * @param name : Name of the pirate ( A-Z )
     */
    public Pirate( char name ) {
        this.name = name;
        this.pirateDislike = new ArrayList<>();
        this.preferenceList = new ArrayList<>();
    }

    /**
     * Return the list of the pirate's ennemies
     * @return pirateDislike
     */
    public ArrayList<Pirate> getPirateDislike() {
        return pirateDislike;
    }

    /**
     * Set the list of the pirates that the pirate dislikes
     * @param pirateDislike : List of the pirates
     */
    public void setPirateDislike(ArrayList<Pirate> pirateDislike) {
        this.pirateDislike = pirateDislike;
    }

    /**
     * Add a pirate in the "pirateDislike"'s list
     * @param autre : A pirate
     */
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

    /**
     * Displays the list of the pirate's ennemies
     */
    public void printDislike(){
        ArrayList<Pirate> list = getPirateDislike();
        for(Pirate p : list){
            System.out.print(p.getName() + " ");
        }
        System.out.println();
    }

    /**
     * Return the name of the pirate
     * @return name
     */
    public char getName() {
        return name;
    }

    /**
     * Set the name of the pirate
     * @param name : Name of the pirate
     */
    public void setName(char name) {
        this.name = name;
    }

    /**
     * Return the preference's list of the pirate
     * @return The preference's list
     */
    public ArrayList<Loot> getPreferenceList() {
        return preferenceList;
    }

    /**
     * Displays the preference's list of the pirate
     */
    public void printPreferenceList() {
        ArrayList<Loot> list = getPreferenceList();
        for(Loot o : list){
            System.out.println(o.getNumber() + " ");
        }
    }

    /**
     * Set the preference's list of the pirate
     * @param preferenceList List of the preference loot sorted from best to worst
     */
    public void setPreferenceList(ArrayList<Loot> preferenceList) {
        this.preferenceList = preferenceList;
    }

    /**
     * Return the loot that the pirate got
     * @return The number of the loot that the pirate got
     */
    public Loot getObjectObtained() {
        return lootObtained;
    }

    /**
     * Set the loot for the pirate
     * @param lootObtained : The loot given to the pirate
     */
    public void setObjectObtained(Loot lootObtained) {
        this.lootObtained = lootObtained;
    }

    /**
     * Displays the preference's list and the Dislike's list of the pirate
     * @return String of the preference's list and the Dislike's list of the pirate
     */
    public String toString() {
        String res = "Pirate " + getName() + ": ";
        for(Loot o : preferenceList){
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
