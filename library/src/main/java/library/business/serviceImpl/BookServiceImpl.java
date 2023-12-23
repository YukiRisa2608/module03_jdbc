package library.business.serviceImpl;

import library.business.dao.IBookDao;
import library.business.daoimpl.BookDaoImpl;
import library.business.model.Book;
import library.business.model.BookDto;
import library.business.service.IBookService;

import java.util.List;

public class BookServiceImpl implements IBookService {
    private static final IBookDao bookDao = new BookDaoImpl();

    @Override
    public List<BookDto> findAll(int displayOption) {
        return bookDao.findAll(displayOption);
    }

    @Override
    public Book findById(Long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<BookDto> searchBooks(String keyword) {
        return bookDao.searchBooks(keyword);
    }

    @Override
    public List<BookDto> getAvailableBooks() {
        return bookDao.getAvailableBooks();
    }

    @Override
    public boolean isBookAvailableForBorrow(Long bookId) {
        return bookDao.isBookAvailableForBorrow(bookId);
    }

    @Override
    public void add(Book s) {
        bookDao.save(s);
    }

    @Override
    public void update(Book s) {
        bookDao.save(s);
    }

    @Override
    public void delete(Long id) {
        bookDao.delete(id);
    }
}
