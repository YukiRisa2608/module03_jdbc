package library.business.model;

import library.business.util.Format;
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

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
                bookId+"", bookName, bookTitle,
                description, authorName, publishersName, Format.formatVND(price.doubleValue()), totalPages+"",
                bookStatus ? "Available" : "UnAvailable"
        );
    }

    public static String title() {
        return String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s",
                "BOOK ID", "BOOK NAME", "BOOK TITLE",
                "DESCRIPTION", "AUTHOR NAME", "PUBLISHER NAME", "PRICE", "TOTAL PAGE",
                "STATUS"
        );
    }
}
