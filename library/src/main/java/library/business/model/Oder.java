package library.business.model;

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
}
