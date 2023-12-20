package ra.bussiness.util;

public class Format {
    public static boolean isValidPhoneNumber(String phoneNumber) {
        while (true) {
            try {
                return phoneNumber.matches("^\\+(?:[0-9] ?){6,14}[0-9]$");
            } catch (Exception e) {
                System.err.println("Phone number is not valid: " + e.getMessage());
            }
        }
    }

}
