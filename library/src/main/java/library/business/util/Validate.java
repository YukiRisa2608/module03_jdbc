package library.business.util;

public class Validate {
    // Hàm kiểm tra xem một chuỗi có độ dài nhỏ hơn 2 hoặc lớn hơn 50 không
    public static boolean isValidLength50(String input) {
        int minLength = 5;
        int maxLength = 50;

        return input.length() >= minLength && input.length() <= maxLength;
    }

    // Hàm kiểm tra xem một chuỗi có độ dài nhỏ hơn 2 hoặc lớn hơn 50 không
    public static boolean isValidLength255(String input) {
        int minLength = 6;
        int maxLength = 255;

        return input.length() >= minLength && input.length() <= maxLength;
    }

    // Hàm kiểm tra xem một chuỗi có phải là số và số đó lớn hơn 0 không
    public static boolean isPositiveNumber(String input) {
        try {
            double number = Double.parseDouble(input);
            return number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
