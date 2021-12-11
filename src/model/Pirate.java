package model;

import java.util.ArrayList;

/**
 *  Class Pirate
 *
 *  name : name of this pirate
 *  pirateDislike : list of pirates that this pirate dislike
 *  preferenceList : list of objects that this pirate prefers
 *  objectObtained : the object that this pirate has obtained by algo or solution
 */
public class Pirate {
    private char name; // Name of the pirate
    private ArrayList<Pirate> pirateDislike; // List of the pirate's enemies
    private ArrayList<Loot> preferenceList; // Pirate preferences list
    private Loot lootObtained = null; // Loot given to the pirate

    public Pirate(){ }

    /**
     * Constructor that creates an object of the class Pirate
     * @param name : Name of the pirate ( A-Z )
     */
    public Pirate( char name ) {
        this.name = name;
        this.pirateDislike = new ArrayList<>();
        this.preferenceList = new ArrayList<>();
    }

    /**
     * Method that returns the list of the pirate's enemies
     * @return the pirate's enemies list
     */
    public ArrayList<Pirate> getPirateDislike() {
        return pirateDislike;
    }

    public int getNumberDislike(){
        return pirateDislike.size();
    }

    /**
     * Method that sets the list of the pirates that the pirate dislikes
     * @param pirateDislike : the pirate's enemies list
     */
    public void setPirateDislike(ArrayList<Pirate> pirateDislike) {
        this.pirateDislike = pirateDislike;
    }

    /**
     * Method that adds a pirate in the "pirateDislike"'s list
     * @param autre : A pirate that the pirate hates
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
     * Method that displays the list of the pirate's enemies
     */
    public void printDislike(){
        ArrayList<Pirate> list = getPirateDislike();
        for(Pirate p : list){
            System.out.print(p.getName() + " ");
        }
        System.out.println();
    }

    public int getPrePriority(Loot loot){
        int index = 1;
        for(Loot l : preferenceList){
            if(l.getNumber() == loot.getNumber()){
                return index;
            }
            index++;
        }
        // Loot obtained doesn't exist in the preferenceList
        try {
            throw new Exception("Loot obtained doesn't exist in the preferenceList.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean getJealous(){
        for(Pirate p : pirateDislike){
            if(this.getPrePriority(p.getObjectObtained()) < this.getPrePriority(lootObtained)){
                return true;
            }
        }
        return false;
    }

    /**
     * Method that returns the name of the pirate
     * @return the value of the variable name
     */
    public char getName() {
        return name;
    }

    /**
     * Method that sets the name of the pirate
     * @param name : New name of the pirate
     */
    public void setName(char name) {
        this.name = name;
    }

    /**
     * Method that returns the preference's list of the pirate
     * @return The preference's list
     */
    public ArrayList<Loot> getPreferenceList() {
        return preferenceList;
    }

    /**
     * Method that displays the preference's list of the pirate
     */
    public void printPreferenceList() {
        ArrayList<Loot> list = getPreferenceList();
        for(Loot o : list){
            System.out.println(o.getNumber() + " ");
        }
    }

    /**
     * Method that sets the preference's list of the pirate
     * @param preferenceList List of the preference loot sorted from best to worst
     */
    public void setPreferenceList(ArrayList<Loot> preferenceList) {
        this.preferenceList = preferenceList;
    }

    /**
     * Method that returns the loot that the pirate got
     * @return The number of the loot that the pirate got
     */
    public Loot getObjectObtained() {
        return lootObtained;
    }

    /**
     * Method that sets the loot for the pirate
     * @param lootObtained : The loot given to the pirate
     */
    public void setObjectObtained(Loot lootObtained) {
        this.lootObtained = lootObtained;
    }

    /**
     * Method that displays the preference's list and the Dislike's list of the pirate
     * @return String of the preference's list and the Dislike's list of the pirate
     */
    public String toString() {
        String res = "Pirate " + getName() + ": ";
        for(Loot o : preferenceList){
            res += o.getNumber() + ", ";
        }
        if(pirateDislike.size() != 0){
            res += "Pirate " + getName() + " dislike ";
            for(Pirate p : pirateDislike){
                res += p.getName() + ", ";
            }
            res += "\n";
            if(lootObtained != null) {
                res += "Pirate got " + "loot " + lootObtained.getNumber() + ", ";
                res += "Pirate is ";
                res += (getJealous()) ? "jealous" : "not jealous";
                res += "\n";
            }
        }
        return res;

    }
}
