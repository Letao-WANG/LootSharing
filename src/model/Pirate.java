package model;

import java.util.ArrayList;

/**
 *  Class Pirate
 *
 *
 */
public class Pirate {
    /**
     * name of pirate
     */
    private char name; // Name of the pirate
    /**
     * List of the pirate's enemies
     */
    private ArrayList<Pirate> pirateDislike; //
    /**
     * Pirate preferences list
     */
    private ArrayList<Loot> preferenceList; //
    /**
     * Loot given to the pirate
     */
    private Loot lootObtained = null;
    private int priority;

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

    /**
     * Method that seek for the position of the loot in the pirate's preference list
     * @param loot Take a loot
     * @return The index of the loot in the pirate's preference list
     */
    public boolean setPrePriority(Loot loot){
        int index = 1;
        for(Loot l : preferenceList){
            if(l.getNumber() == loot.getNumber()){
                priority = index;
                return true;
            }
            index++;
        }
        // Loot obtained doesn't exist in the preferenceList
        try {
            throw new Exception("preference : " + preferenceList + "\nLoot " + loot.getNumber() + " obtained doesn't exist in the preferenceList.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        priority = -1;
        return false;
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

//    public boolean couldReducePriority(){
//        int numberLoot = getPrePriority(lootObtained);
//        for (Pirate pirate : pirateDislike){
//
//        }
//    }

    public Loot getLootObtained() {
        return lootObtained;
    }

    public int getPriority() {
        return priority;
    }

    /**
     * Method that confirms if the pirate will be jealous according to the preference of the others pirates that he dislkes
     * @return true if he will be jealous else false
     */
    public Pirate getJealous(){
        for(Pirate p : pirateDislike){
            if(this.getPrePriority(p.getObjectObtained()) < this.getPrePriority(lootObtained)){
                return p;
            }
        }
        return null;
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
        setPrePriority(lootObtained);
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
                res += (getJealous() != null) ? "jealous to " + getJealous().getName() : "not jealous";
                res += "\n";
            }
        }
        return res;

    }
}
