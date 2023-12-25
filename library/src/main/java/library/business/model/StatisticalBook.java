package library.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor // constructor không tham số
@AllArgsConstructor // constructor full tham số
@Data
public class StatisticalBook {
    private Long bookId;
    private String bookName;
    private int count;

    @Override
    public String toString() {
        return String.format("%-20s%-20s%-20s", bookId+"", bookName+"", count+"");
    }

    public static String title() {
        return String.format("%-20s%-20s%-20s", "BOOK ID", "BOOK NAME", "COUNT");
    }
}
