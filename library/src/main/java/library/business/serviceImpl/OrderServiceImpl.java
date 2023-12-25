package library.business.serviceImpl;

import library.business.dao.IOderDao;
import library.business.daoimpl.OderDaoImpl;
import library.business.model.LibraryRevenue;
import library.business.model.Oder;
import library.business.model.OderDto;
import library.business.model.StatisticalBook;
import library.business.service.IOderService;
import library.business.util.Convert;

import java.util.List;

public class OrderServiceImpl implements IOderService {
    private static final IOderDao oderDao = new OderDaoImpl();

    @Override
    public List<OderDto> findAll(int displayOption) {
        return oderDao.findAll(displayOption);
    }

    @Override
    public void save(Oder oder) {
        oderDao.save(oder);
    }

    @Override
    public OderDto getOrderDetails(Oder oder) {
        return oderDao.getOrderDetails(oder);
    }

    @Override
    public OderDto getOrderById(Long order_id) {
        return Convert.convertOrderToOrderDto(oderDao.findById(order_id));
    }

    @Override
    public List<StatisticalBook> statisticalBorrowBook() {
        return oderDao.statisticalBorrowBook();
    }

    @Override
    public LibraryRevenue calRevenueCurrentYear() {
        return oderDao.calRevenueCurrentYear();
    }

    @Override
    public List<StatisticalBook> displayTop5MostBorrowedBooks() {
        return oderDao.DisplayTop5MostBorrowedBooks();
    }
}
