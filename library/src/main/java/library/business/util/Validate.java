package library.business.util;

public class Validate {
    //Leng of String
    public static boolean isValidLength50(String input) {
        int minLength = 3;
        int maxLength = 50;

        return input.length() >= minLength && input.length() <= maxLength;
    }

    public static boolean isValidLength255(String input) {
        int minLength = 3;
        int maxLength = 255;

        return input.length() >= minLength && input.length() <= maxLength;
    }
    //Number > 0
    public static boolean isPositiveNumber(String input) {
        try {
            double number = Double.parseDouble(input);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
