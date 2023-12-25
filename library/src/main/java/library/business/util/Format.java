package library.business.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class Format {
    public static final SimpleDateFormat spf = new SimpleDateFormat("dd/MM/yyyy");
    public static Date convertToDate(String str){
        while (true){
            try {
                Date date =  spf.parse(str);
                return date;
            } catch (ParseException e) {
                System.err.println("Định dạng không hợp lệ");
            }
        }
    }

    public static String convertToLocalDateFormat(LocalDateTime dateTime) {
        // Định dạng mong muốn: dd/MM/yyyy
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Chuyển đổi LocalDateTime thành chuỗi theo định dạng
        String formattedDate = dateTime.format(formatter);

        return formattedDate;
    }

    public static String convertToDateformat(Date date) {
        // Định dạng mong muốn: dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        // Chuyển đổi Date thành chuỗi theo định dạng
        String formattedDate = dateFormat.format(date);

        return formattedDate;
    }

    //  định dạng tiền tệ VND
    public static String formatVND(double amount) {
        // Tạo đối tượng NumberFormat cho tiền tệ Việt Nam
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        // Format số tiền và trả về chuỗi kết quả
        return currencyFormatter.format(amount);
    }


}


