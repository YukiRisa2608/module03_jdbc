package com.example.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {
    public static String convertToDateTimeFormat(Date date) {
        // Định dạng mong muốn: dd/MM/yyyy HH:mm (ngày/tháng/năm giờ:phút)
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        // Chuyển đổi Date thành chuỗi theo định dạng
        String formattedDate = dateFormat.format(date);

        return formattedDate;
    }

}
