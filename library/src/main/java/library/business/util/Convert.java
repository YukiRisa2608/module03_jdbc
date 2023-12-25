package library.business.util;

import library.business.model.Oder;
import library.business.model.OderDto;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Convert {
    public static LocalDateTime convertDateToLocalDateTime (Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static OderDto convertOrderToOrderDto(Oder order) {
        OderDto orderDto = new OderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setBorrowerName(order.getBorrowerName());
        orderDto.setBookId(order.getBookId());
        orderDto.setOrderAt(Format.convertToLocalDateFormat(order.getOrderAt()));
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setBorrowTime(order.getBorrowTime());
        orderDto.setOrderStatus(order.isOrderStatus());
        return orderDto;
    }

}
