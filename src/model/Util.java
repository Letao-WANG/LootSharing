package model;

import java.util.Scanner;
public class Util {
    /**
     * Used to get the number entered by the user with verifying
     * @param min       Minimum number allowed
     * @param max       Maximum number allowed
     * @return
     */
    public static int getChoiceInt(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int number;
        do {
            System.out.println("Please input the appropriate number : (" + min + " - " + max + ")");
            while (!sc.hasNext(getRegex(max))) {
                String input = sc.next();
                System.out.printf(input + " is not a appropriate number, please input a new one: " + min + " - " + max +"\n");
            }
            number = sc.nextInt();
        } while (number < 0);
        return number;
    }

    /**
     * @see Util#getChoiceInt(int, int)
     */
    public static int getChoiceInt(int min, int max, int exception) {
        int number;
        do{
            number = getChoiceInt(min, max);
        }while(number == exception);
        return number;
    }

    /**
     * Used to get the character entered by the user with verifying
     * @param max
     * @return
     */
    public static char getChoiceChar(char max) {
        Scanner sc = new Scanner(System.in);
        char c = Character.toUpperCase(sc.next().charAt(0));
        while (!(c >= 'A' && c <= 'Z')) {
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
        do{
            c = getChoiceChar(max);
        }while(c == exception);
        return c;
    }

//    public ArrayList<Object> getPiratePreferenceList(char maxPirate, int maxObject) {
//        Scanner sc = new Scanner(System.in);
//        if(this.pirates != null){
//            String line = sc.nextLine();
//        }
//        char c = Character.toUpperCase(sc.next().charAt(0));
//        while (!(c >= 'A' && c <= 'Z')) {
//            System.out.println("Please input the appropriate character : ( A - " +  max + " )");
//            c = Character.toUpperCase(sc.next().charAt(0));
//        }
//        return c;
//    }

    /**
     * Get the regular expression based on the max number
     * @param max value should be 1 to 99
     * @return regular expression
     */
    public static String getRegex(int max){
        if(0<max &&max<10){
            return "[0-"+max+"]";
        } else if(max >= 10 && max <100){
            int onesPlace = max%10;
            int tensPlace = max/10;
            return "[0-9]|[1-"+tensPlace+"]"+"[0-"+onesPlace+"]";
        } else {
            return "[0-9]";
        }
    }
}
