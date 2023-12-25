package library.business.model;

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
}
