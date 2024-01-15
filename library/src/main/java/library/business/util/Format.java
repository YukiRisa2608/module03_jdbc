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
                System.err.println("Invalid format!");
            }
        }
    }

    //Format date
    public static String convertToLocalDateFormat(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = dateTime.format(formatter);
        return formattedDate;
    }

    public static String convertToDateformat(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    //  Format VND
    public static String formatVND(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }


}


