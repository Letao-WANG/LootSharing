package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
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
    public static int getChoiceInt(int maxIntAllowed, Scanner sc) {
        Pattern pattern = Pattern.compile(getIntRegex(maxIntAllowed, true), Pattern.CASE_INSENSITIVE);
        return Integer.parseInt(getInput(pattern, sc));
    }

    /**
     * @param exception number not allowed inputting
     * @see Util#getChoiceInt(int, Scanner)
     */
    public static int getChoiceInt(int maxIntAllowed, Scanner sc, int exception) {
        int number;
        do {
            number = getChoiceInt(maxIntAllowed, sc);
        } while (number == exception);
        return number;
    }

    /**
     * Used to get the character entered by the user with verifying
     *
     * @param maxCharAllowed The limit of the character ( A - maxCharAllowed )
     * @return The character typed by the user
     */
    public static char getChoiceChar(char maxCharAllowed, Scanner sc) {
        char c = Character.toUpperCase(sc.next().charAt(0));
        while (!(c >= 'A' && c <= maxCharAllowed)) {
            System.out.println("Please input the appropriate character : ( A - " + maxCharAllowed + " )");
            c = Character.toUpperCase(sc.next().charAt(0));
        }
        return c;
    }

    /**
     * @param exception char not allowed inputting
     * @see Util#getChoiceChar(char, Scanner)
     */
    public static char getChoiceChar(char maxCharAllowed, Scanner sc, char exception) {
        char c;
        do {
            c = getChoiceChar(maxCharAllowed, sc);
        } while (c == exception);
        return c;
    }

    /**
     * Get the information of pirate's preference
     *
     * @param maxCharAllowed the max character allowed
     * @return e.g., A 1 2 3 when there are 3 pirates, maxCharAllowed is 'C'
     */
    public static String getInputPreference(char maxCharAllowed, Scanner sc) {
        Pattern pattern = Pattern.compile(getPreferenceRegex(maxCharAllowed), Pattern.CASE_INSENSITIVE);
        return getInput(pattern, sc);
    }

    /**
     * Calculate the cost of the naive solution for these pirates, i.e. the number of jealous pirates
     * Calculer le cout de la solution naive pour ces pirates, c'est a dire le nombre de pirate jaloux
     *
     * @param pirates List of the pirates that the user has determined in the terminal
     * @return The number of the jealous pirates
     */
    public static int calculateCost(ArrayList<Pirate> pirates){
        int cost = 0;
        for(Pirate p : pirates){
            if(p.getJealous() != null){
                cost++;
            }
        }
        return cost;
    }

    /**
     * Read name from a String line, convert to list in the format of Integer
     *
     * @param line a String line we read from data file
     * @return a list, every element is the name(or number) of pirate(or loot).
     */
    public static ArrayList<Integer> getNameFromLine(String line) {
        String nameLine = line.substring(line.indexOf("(") + 1, line.indexOf(")"));
        String[] nameArray = nameLine.split(",");
        ArrayList<Integer> names = new ArrayList<>();
        for (String name : nameArray) {
            // add the last character(transform to int) to arrayList<Integer>
            StringBuilder sb = new StringBuilder(name);
            if(name.contains("o")){
                sb.deleteCharAt(0);
            }
//            names.add(Integer.valueOf(name.substring(name.length() - 1)));
            names.add(Integer.valueOf(sb.toString()));
        }
        return names;
    }

    /**
     * Read action from a String line
     *
     * @param line a String line we read from data file
     * @return the name of Class we want to create, e.g. "pirate(***)" will only return "pirate"
     */
    public static String getActionFromLine(String line) {
        return line.substring(0, line.indexOf("("));
    }

    /**
     * Convert int to char
     *
     * @param number format int
     * @return format char
     */
    public static char intToChar(int number) {
        return (char) (number + '0');
    }

    /**
     * Get a random number
     * @param max max number allowed
     * @param min min number allowed
     * @param except number not allowed return
     * @return the random number
     */
    public static int randomInt(int max, int min, int except){
        Random random = new Random();
        int number = (random.nextInt((max - min) + 1)) + min;
        while (number == except){
            number = (random.nextInt((max - min) + 1)) + min;
        }
        return number;
    }

    /**
     * @see Util#randomInt(int, int)
     */
    public static int randomInt(int max, int min){
        Random random = new Random();
        return (random.nextInt((max - min) + 1)) + min;
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
     * @see Util#getChoiceInt(int, Scanner)
     */
    private static String getIntRegex(int maxIntAllowed, boolean matchExactly) {
        String regex = (matchExactly) ? "^" : "";
        if (0 < maxIntAllowed && maxIntAllowed < 10) {
            regex += "[1-" + maxIntAllowed + "]";
        } else if (maxIntAllowed >= 10 && maxIntAllowed < 100) {
            int onesPlace = maxIntAllowed % 10;
            int tensPlace = maxIntAllowed / 10;
            regex += "[1-9]|[1-" + tensPlace + "]" + "[0-" + onesPlace + "]";
        }
        regex += (matchExactly) ? "$" : "";
        return regex;
    }

    /**
     * Input method general, used in other methods input based on pattern
     *
     * @param pattern regular expression pattern, based for verifying the input of user
     * @return the value that the user typed
     * @see Util#getInputPreference(char, Scanner)
     * @see Util#getPreferenceRegex(char)
     */
    private static String getInput(Pattern pattern, Scanner sc) {
        String inputText = sc.nextLine();
        boolean match;
        match = pattern.matcher(inputText).find();

        while (!match) {
            System.out.println(inputText + " is not appropriate, please input a new one:");
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
        StringBuilder regex = new StringBuilder();
        regex.append("^[A-").append(c).append("]");

        for (int i = 0; i < numberObjects; i++) {
            regex.append(" ").append(getIntRegex(numberObjects, false));
        }
        regex.append("$");
        return regex.toString();
    }
}
