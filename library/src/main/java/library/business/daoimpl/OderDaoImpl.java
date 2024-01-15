package library.business.daoimpl;

import library.business.dao.IOderDao;
import library.business.model.LibraryRevenue;
import library.business.model.Oder;
import library.business.model.OderDto;
import library.business.model.StatisticalBook;
import library.business.util.ConnectDB;
import library.business.util.Convert;
import library.business.util.Format;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OderDaoImpl implements IOderDao {
    @Override
    public List<OderDto> findAll(int displayOption) {
        List<OderDto> orderDtos = new ArrayList<>();
        try {
            Connection connection = ConnectDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call GetOrdersByOrderStatus(?)}");
            callableStatement.setInt(1, displayOption);

            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Long orderId = rs.getLong(1);
                String borrower_name = rs.getString(2);
                Long bookId = rs.getLong(3);
                String order_at = Format.convertToDateformat(rs.getDate(4));
                BigDecimal total_price = rs.getBigDecimal(5);
                int borrow_time = rs.getInt(6);
                boolean order_status = rs.getBoolean(7);

                orderDtos.add(new OderDto(orderId, borrower_name, bookId, order_at, total_price, borrow_time, order_status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return orderDtos;
    }

    @Override
    public Oder findById(Long id) {
        Oder oder = null;
        try {
            Connection connection = ConnectDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call GetOrderById(?)}");
            callableStatement.setLong(1, id);

            ResultSet rs = callableStatement.executeQuery();
            if (rs.next()) {

                Long orderId = rs.getLong(1);
                String borrwer_name = rs.getString(2);
                Long bookId = rs.getLong(3);
                LocalDateTime order_at = rs.getObject("order_at", java.time.LocalDateTime.class);
                BigDecimal total_price = rs.getBigDecimal("total_price");
                int borrow_time = rs.getInt(6);
                boolean order_status = rs.getBoolean(7);

                oder = new Oder(orderId, borrwer_name, bookId, order_at, total_price, borrow_time, order_status);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oder;
    }

    @Override
    public void save(Oder order) {
        // Trong phương thức save của OderDaoImpl
        try {
            Connection connection = ConnectDB.openConnection();
            if (order.getOrderId() == null) {
                CallableStatement callableStatement = connection.prepareCall("{call CreateOrder(?, ?)}");
                callableStatement.setString(1, order.getBorrowerName());
                callableStatement.setLong(2, order.getBookId());
                callableStatement.executeUpdate();
            } else {
                CallableStatement callableStatement = connection.prepareCall("{call ReturnBook(?)}");
                callableStatement.setLong(1, order.getOrderId());
                callableStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public OderDto getOrderDetails(Oder order) {
        OderDto oderDto = null;
        try {
            Connection connection = ConnectDB.openConnection();
            CallableStatement callableStatement1 = connection.prepareCall("{call GetOrderDetails(?, ?)}");
            callableStatement1.setString(1, order.getBorrowerName());
            callableStatement1.setLong(2, order.getBookId());
            ResultSet rs = callableStatement1.executeQuery();
            if (rs.next()) {
                oderDto = new OderDto();
                Long orderId = rs.getLong(1);
                oderDto.setOrderId(orderId);
                oderDto.setBookId(order.getBookId());
                oderDto.setBorrowerName(order.getBorrowerName());

                String order_at = Format.convertToDateformat(rs.getDate(4));
                oderDto.setOrderAt(order_at);
                System.out.println("Order ID: " + orderId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return oderDto;
    }

    @Override
    public List<StatisticalBook> statisticalBorrowBook() {
        List<StatisticalBook> statisticalBookList = new ArrayList<>();

        try {
            Connection connection = ConnectDB.openConnection();
            CallableStatement callableStatement = connection.prepareCall("{call CountBookBorrowThisMonth()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("book_id");
                String name = rs.getString("book_name");
                int count = rs.getInt("count");
                StatisticalBook statisticalBook = new StatisticalBook(id, name, count);
                statisticalBookList.add(statisticalBook);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return statisticalBookList;
    }

    @Override
    public LibraryRevenue calRevenueCurrentYear() {
        LibraryRevenue libraryRevenue = null;
        try {
            Connection connection = ConnectDB.openConnection();
            CallableStatement call = connection.prepareCall("{call CalRevenueForCurrentYear()}");

            ResultSet rs = call.executeQuery();
            if (rs.next()){
                libraryRevenue = new LibraryRevenue(rs.getBigDecimal("library_revenue"));
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return libraryRevenue;
    }

    @Override
    public List<StatisticalBook> DisplayTop5MostBorrowedBooks() {
        List<StatisticalBook> statisticalBooks = new ArrayList<>();
        try{
            Connection connection = ConnectDB.openConnection();
            CallableStatement call = connection.prepareCall("{call DisplayTop5MostBorrowedBooks()}");
            ResultSet rs = call.executeQuery();
            while (rs.next()){
                Long id = rs.getLong("book_id");
                String name = rs.getString("book_name");
                int count = rs.getInt("count");
                StatisticalBook statisticalBook = new StatisticalBook(id, name, count);
                statisticalBooks.add(statisticalBook);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return statisticalBooks;
    }
}