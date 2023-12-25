package library.business.model;

import library.business.util.Convert;
import library.business.util.Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
// lombok
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Oder {
    private Long orderId;
    private String borrowerName;
    private Long bookId;
    private LocalDateTime orderAt;
    private BigDecimal totalPrice;
    private int borrowTime;
    private boolean orderStatus;

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
                orderId+"", borrowerName+"", bookId+"",
                Format.convertToLocalDateFormat(orderAt) +"", totalPrice+"", borrowTime+" day",
                orderStatus ? "Paid" : "Unpaid"
        );
    }

    public static String title() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
                "ORDER ID", "BORROWER NAME", "BOOK ID",
                "ORDER AT", "TOTAL PRICE", "BORROW TIME",
                "STATUS"
        );
    }
}
