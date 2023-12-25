package library.present;

import library.business.util.Convert;
import library.business.util.Format;

import java.time.LocalDateTime;
import java.util.Date;

public class Maintest {
    public static void main(String[] args) {
        System.out.println("start...");
        Date date = new Date();

        System.out.println("Date: "+ date);
        LocalDateTime a = Convert.convertDateToLocalDateTime(date);
        String s = Format.convertToLocalDateFormat(a);
        System.out.println("ngay: " + s);
    }
}
