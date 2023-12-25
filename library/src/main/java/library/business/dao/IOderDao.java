package library.business.dao;

import library.business.model.LibraryRevenue;
import library.business.model.Oder;
import library.business.model.OderDto;
import library.business.model.StatisticalBook;

import java.util.List;

public interface IOderDao extends IGenericDao <OderDto, Oder, Long> {
    OderDto getOrderDetails(Oder order);
    List<StatisticalBook> statisticalBorrowBook();
    LibraryRevenue calRevenueCurrentYear();
    List<StatisticalBook> DisplayTop5MostBorrowedBooks();
}
