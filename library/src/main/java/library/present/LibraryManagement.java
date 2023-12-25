package library.present;

import library.business.daoimpl.OderDaoImpl;
import library.business.model.*;
import library.business.service.IBookService;
import library.business.service.IOderService;
import library.business.serviceImpl.BookServiceImpl;
import library.business.serviceImpl.OrderServiceImpl;
import library.business.util.Format;
import library.business.util.InputMethods;
import library.business.util.Validate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LibraryManagement {
    public static IBookService bookService = new BookServiceImpl();
    public static IOderService oderService = new OrderServiceImpl();

    public static void main(String[] args) {
        while (true) {
            System.out.println("=======================MENU=======================");
            System.out.println("1. Display All Books");
            System.out.println("2. Display All Available books");
            System.out.println("3. Display All Orders");
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
                    displayAllOrders();
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
                    returnBook();
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
                    displayBooksBorrowedThisMonth();
                    break;
                case 12:
                    displayLibraryRevenueThisYear();
                    break;
                case 13:
                    displayTop5MostBorrowedBooks();
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
            System.out.println(BookDto.title());
            for (BookDto book : allBooks) {
                System.out.println(book);
            }
        }
    }

    //2. Display Available Books
    private static int displayAvailableBooks() {
        System.out.println("------ Display Available Books ------");
        List<BookDto> availableBooks = bookService.findAll(2);
        if (availableBooks.isEmpty()) {
            System.out.println("No available books.");
            return 0;
        } else {
            System.out.println(BookDto.title());
            for (BookDto book : availableBooks) {
                System.out.println(book);
            }
        }
        return 1;
    }

    //3. Display Borrowed Books
    private static void displayAllOrders() {
        Byte status;
        do {
            System.out.println("== Choice Status ==");
            System.out.println("0. Unpaid");
            System.out.println("1. Paid");
            status = InputMethods.getByte();
            if (status != 0 && status != 1) {
                System.out.println("status must be 0 or 1");
            }
        } while (status != 0 && status != 1);
        System.out.println("------ Display All Orders ------");
        List<OderDto> oderDtos = oderService.findAll(status);
        if (oderDtos.isEmpty()) {
            System.out.println("No books are borrowed.");
        } else {
            System.out.println(OderDto.title());
            for (OderDto oder : oderDtos) {
                System.out.println(oder);
            }
        }
    }

    //4. Find book by id
    private static void findBookById() {
        System.out.println("Enter book id to find: ");
        Long id = InputMethods.getLong();
        Book book = bookService.findById(id);
        if (book == null) {
            System.out.println("Not found book by id: " + id);
            return;
        }
        System.out.println(Book.title());
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

            System.out.println(Book.title());
            for (BookDto book : result) {
                System.out.println(book);
            }
        }
    }

    //6. Borrow Book
    private static void borrowBook() {
        //Step 1: Display all available book
        System.out.println("Select a book to borrow:");
        int result = displayAvailableBooks();
        if (result == 0) {
            return;
        }

        //Step 2: Input borrower name and book id
        Long bookId = null;
        String borrowerName = null;
        // Validate borrower name
        do {
            System.out.println("Enter borrower's name: ");
            borrowerName = InputMethods.getString();
        } while (!Validate.isValidLength50(borrowerName));
        // Validate book ID
        do {
            System.out.println("Enter book ID to borrow: ");
            bookId = InputMethods.getLong();
        } while (bookId == null || !bookService.isBookAvailableForBorrow(bookId));

        // Step 3: Create Order
        Oder order = new Oder();
        order.setBorrowerName(borrowerName);
        order.setBookId(bookId);
        order.setOrderAt(LocalDateTime.now());
        order.setOrderStatus(false);

        //Step 4: Save Oder in Oder Table and hide borrowed book
        oderService.save(order);
        OderDto oderDto = oderService.getOrderDetails(order);

        //Step 5: Display oder detail
        displayOrderDetails(oderDto);

    }

    private static void displayOrderDetails(OderDto order) {
        if (order == null) {
            System.err.println("No oder to display");
            return;
        }
        Long orderId = order.getOrderId();
        if (orderId == null) {
            System.err.println("Order ID is null");
        } else {
            System.out.println("Order ID: " + orderId);
        }
        System.out.println("------ Order Details ------");
        System.out.println("Order ID: " + order.getOrderId());
        System.out.println("Borrower Name: " + order.getBorrowerName());
        System.out.println("Borrowed Book: " + order.getBookId());
        System.out.println("Order At: " + order.getOrderAt());
        System.out.println("Order Status: " + (order.isOrderStatus() ? "Paid" : "Unpaid"));
        if (order.isOrderStatus()){
            System.out.println("Borrow Time: " + order.getBorrowTime() + " day");
            System.out.println("Total Price: " + Format.formatVND(order.getTotalPrice().doubleValue()));
        }
    }

    //7. Return Book
    private static void returnBook() {
        System.out.println("ENter order id: ");
        Long orderId = InputMethods.getLong();
        Oder order = new Oder();
        order.setOrderId(orderId);
        oderService.save(order);
        OderDto oderDto = oderService.getOrderById(orderId);
        displayOrderDetails(oderDto);
    }

    //8. Add new book
    private static void createANewBook() {
        System.out.println("Enter the number of books you want to add: ");
        Byte numberOfBooks = InputMethods.getByte();

        for (Byte i = 0; i < numberOfBooks; i++) {
            System.out.println("Enter details for book " + (i + 1) + ":");
            Book book = new Book();
            book.inputBook();
            bookService.add(book);
//            System.out.println("Book " + (i + 1) + " added successfully.");
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
        }else if (!book.isBookStatus()){
            System.out.println("book have been borrowed");
            return;
        }

        System.out.println("Current book details:");
        System.out.println(Book.title());
        System.out.println(book);
        System.out.println("Enter new details for the book:");
        book.inputBook();
        bookService.update(book);
//        System.out.println("Book details updated successfully.");
    }

    //10. Delete book by ID
    private static void deleteBookById() {
        System.out.println("Enter book ID to delete:");
        Long bookId = InputMethods.getLong();
        bookService.delete(bookId);
//        System.out.println("Book deleted successfully.");
    }

    //11. count book borrow this month
    private static void displayBooksBorrowedThisMonth() {
        List<StatisticalBook> statisticalBookList = oderService.statisticalBorrowBook();
        System.out.println(StatisticalBook.title());
        for (StatisticalBook  t: statisticalBookList){
            System.out.println(t);
        }
    }

    //12.cal revenue current year
    private static void displayLibraryRevenueThisYear() {
        LibraryRevenue  libraryRevenue = oderService.calRevenueCurrentYear();
        System.out.println("Revenue Of Current Year: " + Format.formatVND(libraryRevenue.getLibrary_revenue().doubleValue()));
    }

    //13. display top 5 most borrow
    private static void displayTop5MostBorrowedBooks() {
        List<StatisticalBook> statisticalBookList = oderService.displayTop5MostBorrowedBooks();
        System.out.println(StatisticalBook.title());
        for (StatisticalBook b:statisticalBookList){
            System.out.println(b);
        }
    }
}
