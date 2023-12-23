package library.business.service;

import library.business.model.Book;
import library.business.model.BookDto;

import java.util.List;

public interface IBookService {
    List<BookDto> findAll(int displayOption);
    Book findById(Long id);
    List<BookDto> searchBooks(String keyword);
    List<BookDto> getAvailableBooks();
    boolean isBookAvailableForBorrow(Long bookId);
    void add(Book s);
    void update(Book s);
    void delete(Long id);
}
