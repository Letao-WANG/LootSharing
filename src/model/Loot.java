package model;

/**
 * Objets attribués par les pirates
 *
 * Items allocated by pirates
 */
public class Loot {
    /**
     * Number of the loot
     */
    private int number;
    /**
     * If the loot is taken by a pirate or not
     */
    private boolean token = false ;

    /**
     * Constructor that creates an object of the class Loot
     * @param number Number of the loot
     */
    public Loot(int number) {
        this.number = number;
    }

    /**
     * Method that returns the number of the loot
     * @return the value of the variable number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Method that sets the new number of the loot
     * @param number the new number of the loot
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * Method that determines whether the loot is taken
     * @return true if the loot is taken else false
     */
    public boolean isToken() {
        return token;
    }

    /**
     * Method that sets the value of the variable token
     * @param token true if the token is taken else false
     */
    public void setToken(boolean token) {
        this.token = token;
    }

    /**
     * Method that displays the number of the loot in String
     * @return The value of the variable number in String
     */
    public String toString() {
        return String.valueOf(number);
    }
}
