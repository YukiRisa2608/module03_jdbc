package library.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@NoArgsConstructor // constructor không tham số
@AllArgsConstructor // constructor full tham số
@Data
public class BookDto {
    private Long bookId;
    private String bookName;
    private String bookTitle;
    private String description;
    private String authorName;
    private String publishersName;
    private BigDecimal price;
    private int totalPages;
    private boolean bookStatus;
}
