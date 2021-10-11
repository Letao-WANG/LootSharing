package model;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    /**
     * Used to get the number entered by the user with verifying
     *
     * WARNING ! can't distinguish such as "a 2"
     *
     * @param min Minimum number allowed
     * @param max Maximum number allowed
     * @return
     */
    public static int getChoiceInt(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int number;
        System.out.println("Please input the appropriate number : (" + min + " - " + max + ")");
        while (!sc.hasNext(getIntRegex(max))) {
            String input = sc.next();
            System.out.printf(input + " is not a appropriate number, please input a new one: " + min + " - " + max + "\n");
        }
        number = sc.nextInt();
        return number;
    }

    /**
     * @see Util#getChoiceInt(int, int)
     */
    public static int getChoiceInt(int min, int max, int exception) {
        int number;
        do {
            number = getChoiceInt(min, max);
        } while (number == exception);
        return number;
    }

    /**
     * Used to get the character entered by the user with verifying
     *
     * @param max
     * @return
     */
    public static char getChoiceChar(char max) {
        Scanner sc = new Scanner(System.in);
        char c = Character.toUpperCase(sc.next().charAt(0));
        while (!(c >= 'A' && c <= max)) {
            System.out.println("Please input the appropriate character : ( A - " + max + " )");
            c = Character.toUpperCase(sc.next().charAt(0));
        }
        return c;
    }

    /**
     * @see Util#getChoiceChar(char)
     */
    public static char getChoiceChar(char max, char exception) {
        char c;
        do {
            c = getChoiceChar(max);
        } while (c == exception);
        return c;
    }

    /**
     * Get the regular expression based on the max number, for verifying the input of user in methode getChoiceInt
     *
     * @param max biggest number allowed, value should be 1 to 26
     * @return regular expression
     * @see Util#getChoiceInt(int, int)
     */
    public static String getIntRegex(int max) {
        if (0 < max && max < 10) {
            return "[1-" + max + "]$";
        } else if (max >= 10 && max < 100) {
            int onesPlace = max % 10;
            int tensPlace = max / 10;
            return "[1-9]|[1-" + tensPlace + "]" + "[0-" + onesPlace + "]$";
        } else {
            return "";
        }
    }

    public static String getInputPreference(char maxCharAllowed) {
        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();
        boolean match;
        Pattern pattern = Pattern.compile(getStringRegex(maxCharAllowed), Pattern.CASE_INSENSITIVE);
        match = pattern.matcher(inputText).find();

        while (!match) {
            System.out.printf(inputText + " is not appropriate, please input a new one:\n");
            inputText = sc.nextLine();
            match = pattern.matcher(inputText).find();
        }

        return inputText;
    }

    public static String getInput(Pattern pattern, char maxCharAllowed) {
        Scanner sc = new Scanner(System.in);
        String inputText = sc.nextLine();
        boolean match;
        match = pattern.matcher(inputText).find();

        while (!match) {
            System.out.printf(inputText + " is not appropriate, please input a new one:\n");
            inputText = sc.nextLine();
            match = pattern.matcher(inputText).find();
        }

        return inputText;
    }

    /**
     * Get the regular expression based on the max number char, for verifying the input of user in methode
     *
     * @param maxCharAllowed biggest number allowed, value should be 1 to 26
     * @return regular expression
     */
    public static String getStringRegex(char maxCharAllowed) {
        char c = Character.toUpperCase(maxCharAllowed);
        int numberObjects = c - 'A' + 1;
        String regex = "";
        regex += "^[A-" + c + "]";

        for (int i = 0; i < numberObjects; i++) {
            regex += " " + getIntRegex(numberObjects);
        }
        regex += "$";
        return regex;
    }
}
