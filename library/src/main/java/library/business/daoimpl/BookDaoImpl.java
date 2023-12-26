package library.business.daoimpl;

import library.business.dao.IBookDao;
import library.business.model.Book;
import library.business.model.BookDto;
import library.business.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements IBookDao {
    @Override
    public List<BookDto> findAll(int displayOption) {
        Connection conn = ConnectDB.openConnection();
        List<BookDto> bookDtos = new ArrayList<>();
        String storedProcedure;

        try {
            // Chọn stored procedure phù hợp dựa trên displayOption
            if (displayOption == 1) {
                storedProcedure = "{call ShowAllBooks()}";
            } else if (displayOption == 2) {
                storedProcedure = "{call ShowAvailableBooks()}";
            } else if (displayOption == 3) {
                storedProcedure = "{call ShowBorrowedBooks()}";
            } else {
                throw new IllegalArgumentException("Invalid choice!");
            }

            CallableStatement call = conn.prepareCall(storedProcedure);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BookDto s = new BookDto();
                s.setBookId(rs.getLong("book_id"));
                s.setBookName(rs.getString("book_name"));
                s.setBookTitle(rs.getString("book_title"));
                s.setDescription(rs.getString("description"));
                s.setAuthorName(rs.getString("author_name"));
                s.setPublishersName(rs.getString("publishers_name"));
                s.setPrice(rs.getBigDecimal("price"));
                s.setTotalPages(rs.getInt("total_pages"));
                s.setBookStatus(rs.getBoolean("book_status"));
                bookDtos.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn); // đóng kết nối
        }
        return bookDtos;
    }

    @Override
    public Book findById(Long id) {
        Connection conn = ConnectDB.openConnection();
        Book s = null;
        try {
            CallableStatement call = conn.prepareCall("{call GetBookDetails(?)}");
            call.setLong(1, id);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                s = new Book();
                s.setBookId(rs.getLong("book_id"));
                s.setBookName(rs.getString("book_name"));
                s.setBookTitle(rs.getString("book_title"));
                s.setDescription(rs.getString("description"));
                s.setAuthorName(rs.getString("author_name"));
                s.setPublishersName(rs.getString("publishers_name"));
                s.setPrice(rs.getBigDecimal("price"));
                s.setTotalPages(rs.getInt("total_pages"));
                s.setBookStatus(rs.getBoolean("book_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn); // đóng kết nôi
        }
        return s;
    }

    @Override
    public void save(Book book) {
        Connection conn = ConnectDB.openConnection();
        CallableStatement call = null;
        Book s = null;
        try {
            CallableStatement callOldBook = conn.prepareCall("{call FindBookByName(?)}");
            callOldBook.setString(1, book.getBookName());
            ResultSet rs = callOldBook.executeQuery();
            if (rs.next()){
                s = new Book();
                s.setBookId(rs.getLong("book_id"));
                s.setBookName(rs.getString("book_name"));
//                s.setBookTitle(rs.getString("book_title"));
//                s.setDescription(rs.getString("description"));
//                s.setAuthorName(rs.getString("author_name"));
//                s.setPublishersName(rs.getString("publishers_name"));
//                s.setPrice(rs.getBigDecimal("price"));
//                s.setTotalPages(rs.getInt("total_pages"));
//                s.setBookStatus(rs.getBoolean("book_status"));
            }

            if (book.getBookId() == null) {
                // chức năng thêm mới
                if (s != null && book.getBookName().equals(s.getBookName())){
                    System.out.println("Duplicate Book Name");
                    return;
                }
                call = conn.prepareCall("{call AddNewBook(?, ?, ?, ?, ?, ?, ?)}");
                call.setString(1, book.getBookName());
                call.setString(2, book.getBookTitle());
                call.setString(3, book.getDescription());
                call.setString(4, book.getAuthorName());
                call.setString(5, book.getPublishersName());
                call.setBigDecimal(6, book.getPrice());
                call.setInt(7, book.getTotalPages());
            } else {
                // chức năng cập nhật
                if (s != null &&  book.getBookId() != s.getBookId() && book.getBookName().equals(s.getBookName())){
                    System.out.println("Duplicate Book Name");
                    return;
                }
                call = conn.prepareCall("{call UpdateBookDetail(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
                call.setLong(1, book.getBookId());
                call.setString(2, book.getBookName());
                call.setString(3, book.getBookTitle());
                call.setString(4, book.getDescription());
                call.setString(5, book.getAuthorName());
                call.setString(6, book.getPublishersName());
                call.setBigDecimal(7, book.getPrice());
                call.setInt(8, book.getTotalPages());
                call.setBoolean(9, book.isBookStatus());
            }
            call.executeUpdate();
            System.out.println("Successfull!!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn); // đóng kết nối
        }
    }

    @Override
    public void delete(Long id) {
        Connection conn = ConnectDB.openConnection();
        try {
            CallableStatement call = conn.prepareCall("{call DeleteBook(?)}");
            call.setLong(1, id);
            call.executeUpdate();
            System.out.println("Successfull!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            ConnectDB.closeConnection(conn); // đóng kết nối
        }
    }

    @Override
    public List<BookDto> searchBooks(String keyword) {
        Connection conn = ConnectDB.openConnection();
        List<BookDto> bookDtos = new ArrayList<>();
        try {
            String storedProcedure = "{call SearchBooks(?)}";
            CallableStatement call = conn.prepareCall(storedProcedure);
            call.setString(1, keyword);
            ResultSet rs = call.executeQuery();
            while (rs.next()) {
                BookDto book = new BookDto();
                book.setBookId(rs.getLong("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookTitle(rs.getString("book_title"));
                book.setDescription(rs.getString("description"));
                book.setAuthorName(rs.getString("author_name"));
                book.setPublishersName(rs.getString("publishers_name"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setTotalPages(rs.getInt("total_pages"));
                book.setBookStatus(rs.getBoolean("book_status"));
                bookDtos.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return bookDtos;
    }

    @Override
    public BookDto findBookByName(String bookName) {
        Connection conn = ConnectDB.openConnection();
        BookDto book = null;
        try {
            String storedProcedure = "{call FindBookByName(?)}";
            CallableStatement call = conn.prepareCall(storedProcedure);
            call.setString(1, bookName);
            ResultSet rs = call.executeQuery();
            if (rs.next()) {
                book = new BookDto();
                book.setBookId(rs.getLong("book_id"));
                book.setBookName(rs.getString("book_name"));
                book.setBookTitle(rs.getString("book_title"));
                book.setDescription(rs.getString("description"));
                book.setAuthorName(rs.getString("author_name"));
                book.setPublishersName(rs.getString("publishers_name"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setTotalPages(rs.getInt("total_pages"));
                book.setBookStatus(rs.getBoolean("book_status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            ConnectDB.closeConnection(conn);
        }
        return book;
    }

    @Override
    public List<BookDto> getAvailableBooks() {
        return findAll(2);
    }

    @Override
    public boolean isBookAvailableForBorrow(Long bookId) {
        List<BookDto> availableBooks = findAll(2);
        return availableBooks.stream().anyMatch(bookDto -> bookDto.getBookId().equals(bookId));
    }
}
