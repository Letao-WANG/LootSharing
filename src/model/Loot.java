package model;

/**
 * Objets attribués par les pirates
 *
 * Items allocated by pirates
 */
public class Loot {
    private int number;
    private boolean token;

    public Loot(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isToken() {
        return token;
    }

    public void setToken(boolean token) {
        this.token = token;
    }

    public String toString() {
        return String.valueOf(number);
    }
}
