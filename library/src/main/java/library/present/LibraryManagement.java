package library.present;

import library.business.model.Book;
import library.business.model.BookDto;
import library.business.model.OderDto;
import library.business.service.IBookService;
import library.business.serviceImpl.BookServiceImpl;
import library.business.util.InputMethods;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagement {
    public static IBookService bookService = new BookServiceImpl();
    public static void main(String[] args) {
        while (true) {
            System.out.println("=======================MENU=======================");
            System.out.println("1. Display All Books");
            System.out.println("2. Display All Available books");
            System.out.println("3. Display All Borrowing Books");
            System.out.println("4. Find book by id");
            System.out.println("5. Search Books by Title, Author, or any Keywords");
            System.out.println("6. Borrow a Book");
            System.out.println("7. Return a Book");
            System.out.println("8. Create A New Book");
            System.out.println("9. Edit Detail Book");
            System.out.println("10. Delete Book by ID");
            System.out.println("11. Display Number of Books Borrowed This Month");
            System.out.println("12. Display Library Revenue This Year");
            System.out.println("13. Display Top 5 Most Borrowed Books");
            System.out.println("0. Exit");
            System.out.println("ENTER YOUR CHOICE: ");

            byte choice = InputMethods.getByte();
            switch (choice) {
                case 1:
                    displayAllBooks();
                    break;
                case 2:
                    displayAvailableBooks();
                    break;
                case 3:
                    displayBorrowedBooks();
                    break;
                case 4:
                    findBookById();
                    break;
                case 5:
                    searchBookByKeyWord();
                    break;
                case 6:
                    borrowBook();
                    break;
                case 7:
                    //returnBook();
                    break;
                case 8:
                    createANewBook();
                    break;
                case 9:
                    editBookDetails();
                    break;
                case 10:
                    deleteBookById();
                    break;
                case 11:
                    //displayBooksBorrowedThisMonth();
                    break;
                case 12:
                    //displayLibraryRevenueThisYear();
                    break;
                case 13:
                    //displayTop5MostBorrowedBooks();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid Choice!");
                    break;
            }
        }
    }

    //"""""""""""""METHODS""""""""""""""""""""

    //1. Display All Books
    private static void displayAllBooks() {
        System.out.println("------ Display All Books ------");
        List<BookDto> allBooks = bookService.findAll(1);
        if (allBooks.isEmpty()) {
            System.out.println("No books available.");
        } else {
            for (BookDto book : allBooks) {
                System.out.println(book);
            }
        }
    }

    //2. Display Available Books
    private static void displayAvailableBooks() {
        System.out.println("------ Display Available Books ------");
        List<BookDto> availableBooks = bookService.findAll(2);
        if (availableBooks.isEmpty()) {
            System.out.println("No available books.");
        } else {
            for (BookDto book : availableBooks) {
                System.out.println(book);
            }
        }
    }

    //3. Display Borrowed Books
    private static void displayBorrowedBooks() {
        System.out.println("------ Display Borrowed Books ------");
        List<BookDto> borrowingBooks = bookService.findAll(3);
        if (borrowingBooks.isEmpty()) {
            System.out.println("No books are borrowed.");
        } else {
            for (BookDto book : borrowingBooks) {
                System.out.println(book);
            }
        }
    }

    //4. Find book by id
    private static void findBookById() {
        System.out.println("Enter book id to find: ");
        Long id = InputMethods.getLong();
        Book book = bookService.findById(id);
        if (book == null){
            System.out.println("Not found book by id: " + id);
            return;
        }
        System.out.println(book);
    }

    //5. Search book by key word
    private static void searchBookByKeyWord() {
        System.out.println("Enter Title, Author, or any Keywords to search:");
        String keyword = InputMethods.getString();
        List<BookDto> result = bookService.searchBooks(keyword);
        if (result.isEmpty()) {
            System.out.println("No books found matching with key word: " + keyword);
        } else {
            System.out.println("Books found:");
            for (BookDto book : result) {
                System.out.println(book);
            }
        }
    }

    //6. Borrow Book
    private static void borrowBook() {
        //Step 1: Display all available book
        System.out.println("Select a book to borrow:");
        displayAvailableBooks();

        //Step 2: Input borrower name and book id
        //Input quantity
        System.out.println("Enter the number of books to borrow: ");
        int numberOfBooks = InputMethods.getInteger();
        //Add to oder list
        List<Long> borrowList = new ArrayList<>();
        int count = 0;
        Long bookId = null;
        while (count < numberOfBooks) {
            System.out.println("Enter book ID to borrow. Book number " + (count + 1) + ":");
            bookId = InputMethods.getLong();
            if (!bookService.isBookAvailableForBorrow(bookId)) {
                System.out.println("Book ID " + bookId + " is not available for borrowing. Please choose another book.");
            } else {
                borrowList.add(bookId);
                count++;
            }
        }
        //Input borrower name
        System.out.println("Enter borrower's name: ");
        String borrowerName = InputMethods.getString();
        //Check available book ưhith ID
        if (!bookService.isBookAvailableForBorrow(bookId)) {
            System.out.println("Selected book is not available for borrowing.");
            return;
        }

        //Step 3: Create Oder
        OderDto order = new OderDto();
        order.setBorrowerName(borrowerName);
        order.setOrderAt(LocalDateTime.now());
        order.setOrderStatus(false);
        List<Long> borrowedBookIds = new ArrayList<>();
        for (Long idBook : borrowList) {
            borrowedBookIds.add(idBook);
        }
        order.setBorrowedBookIds(borrowedBookIds);

        //Step 4: Save Oder in Oder Table and hide borrowed book
        // order = orderDao.save(order);

// Hiển thị thông tin hóa đơn
        // displayOrderDetails(order);

    }
    //7. Return Book

    //8. Add new book
    private static void createANewBook() {
        System.out.println("Enter the number of books you want to add: ");
        Byte numberOfBooks = InputMethods.getByte();

        for (Byte i = 0; i < numberOfBooks; i++) {
            System.out.println("Enter details for book " + (i + 1) + ":");
            Book book = new Book();
            book.inputBook();
            bookService.add(book);
            System.out.println("Book " + (i + 1) + " added successfully.");
        }

    }

    //9. Edit book by ID
    private static void editBookDetails() {
        System.out.println("Enter book ID to edit:");
        Long bookId = InputMethods.getLong();
        Book book = bookService.findById(bookId);
        if (book == null) {
            System.out.println("Book not found by ID: " + bookId);
            return;
        }
        System.out.println("Current book details:");
        System.out.println(book);
        System.out.println("Enter new details for the book:");
        book.inputBook();
        bookService.update(book);
        System.out.println("Book details updated successfully.");
    }

    //10. Delete book by ID
    private static void deleteBookById() {
        System.out.println("Enter book ID to delete:");
        Long bookId = InputMethods.getLong();
        bookService.delete(bookId);
        System.out.println("Book deleted successfully.");
    }

}
