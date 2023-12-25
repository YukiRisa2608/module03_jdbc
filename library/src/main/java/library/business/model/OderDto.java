package library.business.model;

import library.business.util.Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

// lombok
@NoArgsConstructor
@AllArgsConstructor
@Data

public class OderDto {
    private Long orderId;
    private String borrowerName;
    private Long bookId;
    private String orderAt;
    private BigDecimal totalPrice;
    private int borrowTime;
    private boolean orderStatus;

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
                orderId+"", borrowerName+"", bookId+"",
                orderAt+"", totalPrice +"", borrowTime+" day",
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
