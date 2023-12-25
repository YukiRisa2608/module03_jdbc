package library.business.model;

import library.business.util.Format;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor // constructor không tham số
@AllArgsConstructor // constructor full tham số
@Data
public class LibraryRevenue {
    private BigDecimal library_revenue;
    @Override
    public String toString() {
        return String.format("%-20s", Format.formatVND(library_revenue.doubleValue()) + "");
    }

    public static String title() {
        return String.format("%-20s",  "REVENUE");
    }
}
