package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Util, mainly providing the various methods needed for user interaction (input and output)
 */
public class Util {

    /*--------------------------------------------------------------------------
    ---- PART PUBLIC METHODS, it means we can use these methods in other class -
    --------------------------------------------------------------------------*/

    /**
     * Used to get the number entered by the user with verifying
     *
     * @param maxIntAllowed Maximum number allowed
     * @return The number in Integer
     */
    public static int getChoiceInt(int maxIntAllowed) {
        Pattern pattern = Pattern.compile(getIntRegex(maxIntAllowed, true), Pattern.CASE_INSENSITIVE);
        return Integer.parseInt(getInput(pattern));
    }

    /**
     * @see Util#getChoiceInt( int)
     * @param exception number not allowed inputting
     */
    public static int getChoiceInt(int maxIntAllowed, int exception) {
        int number;
        do {
            number = getChoiceInt(maxIntAllowed);
        } while (number == exception);
        return number;
    }

    /**
     * Used to get the character entered by the user with verifying
     *
     * @param maxCharAllowed The limit of the character ( A - maxCharAllowed )
     * @return The character typed by the user
     */
    public static char getChoiceChar(char maxCharAllowed) {
        Scanner sc = new Scanner(System.in);
        char c = Character.toUpperCase(sc.next().charAt(0));
        while (!(c >= 'A' && c <= maxCharAllowed)) {
            System.out.println("Please input the appropriate character : ( A - " + maxCharAllowed + " )");
            c = Character.toUpperCase(sc.next().charAt(0));
        }
        return c;
    }

    /**
     * @see Util#getChoiceChar(char)
     * @param exception char not allowed inputting
     */
    public static char getChoiceChar(char maxCharAllowed, char exception) {
        char c;
        do {
            c = getChoiceChar(maxCharAllowed);
        } while (c == exception);
        return c;
    }

    /**
     * Get the information of pirate's preference
     * @param maxCharAllowed
     * @return e.g., A 1 2 3 when there are 3 pirates, maxCharAllowed is 'C'
     */
    public static String getInputPreference(char maxCharAllowed) {
        Pattern pattern = Pattern.compile(getPreferenceRegex(maxCharAllowed), Pattern.CASE_INSENSITIVE);
        return getInput(pattern);
    }

    /**
     * Calculate the cost of the naive solution for these pirates, i.e. the number of jealous pirates
     * Calculer le cout de la solution naive pour ces pirates, c'est a dire le nombre de pirate jaloux
     *
     *
     *
     * @param pirates List of the pirates that the user has determined in the terminal
     * @return The number of the jealous pirates
     */
    public static int calculateCost(ArrayList<Pirate> pirates){
        int nbJealous = 0;
        HashMap<Character, Integer> compteurs = new HashMap<>();
        for (Pirate p : pirates) {
            int i = 0;
            for(int k = 0; k<p.getPreferenceList().size(); k++){
                if((p.getObjectObtained().getNumber() == (p.getPreferenceList().get(k).getNumber()))){
                    compteurs.put(p.getName(), k);
                }
            }
        }
        for (Pirate p : pirates) {
            for (int i = 0; i < p.getPirateDislike().size(); i++) {
                if (compteurs.get(p.getPirateDislike().get(i).getName()) != ((compteurs.get(p.getName())))) {
                    nbJealous++;
                }
            }
        }


        return nbJealous/2;
    }

    /*--------------------------------------------------------
    ------ PART PRIVATE METHODS, we don't use them directly, -
    ------ so we don’t have to pay too much attention --------
    --------------------------------------------------------*/

    /**
     * Get the regular expression based on the max number, for verifying the input of user in methode getChoiceInt
     *
     * @param maxIntAllowed biggest number allowed, value should be 1 to 26
     * @return regular expression
     * @see Util#getChoiceInt(int)
     */
    private static String getIntRegex(int maxIntAllowed, boolean matchExactly) {
        String regex = (matchExactly)? "^" : "";
        if (0 < maxIntAllowed && maxIntAllowed < 10) {
            regex += "[1-" + maxIntAllowed + "]";
        } else if (maxIntAllowed >= 10 && maxIntAllowed < 100) {
            int onesPlace = maxIntAllowed % 10;
            int tensPlace = maxIntAllowed / 10;
            regex += "[1-9]|[1-" + tensPlace + "]" + "[0-" + onesPlace + "]";
        }
        regex += (matchExactly)? "$" : "";
        return regex;
    }

    /**
     * Input method general, used in other methods input based on pattern
     * @see Util#getInputPreference(char) 
     * @see Util#getPreferenceRegex(char)
     * @param pattern regular expression pattern, based for verifying the input of user
     * @return the value that the user typed
     */
    private static String getInput(Pattern pattern) {
        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();
        boolean match;
        match = pattern.matcher(inputText).find();

        while (!match) {
            System.out.printf(inputText + " is not appropriate, please input a new one:\n");
            System.out.println("Pattern : " + pattern);
            inputText = sc.nextLine();
            match = pattern.matcher(inputText).find();
        }
        return inputText;
    }

    /**
     * Get the regular expression based on the max number char, for verifying the input of user in methode
     * For example, if the maxCharAllowed is 'C', (which means there are 3 pirates)
     * the regular expression is ^[A-C] [1-3] [1-3] [1-3]$ correspondent with A 1 2 3
     *
     * @param maxCharAllowed biggest number allowed, value should be 1 to 26
     * @return regular expression ( e.g. ^[A-C] [1-3] [1-3] [1-3]$ )
     */
    private static String getPreferenceRegex(char maxCharAllowed) {
        char c = Character.toUpperCase(maxCharAllowed);
        int numberObjects = c - 'A' + 1;
        String regex = "";
        regex += "^[A-" + c + "]";

        for (int i = 0; i < numberObjects; i++) {
            regex += " " + getIntRegex(numberObjects, false);
        }
        regex += "$";
        return regex;
    }
}
