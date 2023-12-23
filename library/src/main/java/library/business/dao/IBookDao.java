package library.business.dao;

import library.business.model.Book;
import library.business.model.BookDto;

import java.util.List;

public interface IBookDao extends IGenericDao<BookDto, Book, Long> {
    List<BookDto> searchBooks(String keyword);
    List<BookDto> getAvailableBooks();
    boolean isBookAvailableForBorrow(Long bookId);
}
