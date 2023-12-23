package library.business.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    //  định dạng tiền tệ VND
    public static String formatVND(double amount) {
        Locale localeVN = new Locale("vi", "VN");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(localeVN);

        return currencyFormatter.format(amount);
    }


}


