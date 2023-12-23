package library.business.model;

import library.business.util.InputMethods;
import library.business.util.Validate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Scanner;

// lombok
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Book {
    private Long bookId;
    private String bookName;
    private String bookTitle;
    private String description;
    private String authorName;
    private String publishersName;
    private BigDecimal price;
    private int totalPages;
    private boolean bookStatus;

    public void inputBook() {
        //book name
        do {
            System.out.println("Enter book name: ");
            bookName = InputMethods.getString();
            if (!Validate.isValidLength50(bookName)) {
                System.out.println("length must from 2 to 50");
            }
        } while (!Validate.isValidLength50(bookName));

        //book title
        do {
            System.out.println("Enter book title: ");
            bookTitle = InputMethods.getString();
            if (!Validate.isValidLength50(bookTitle)) {
                System.out.println("length must from 2 to 50");
            }
        } while (!Validate.isValidLength50(bookTitle));

        //description
        do {
            System.out.println("Enter book description: ");
            description = InputMethods.getString();
            if (!Validate.isValidLength255(description)) {
                System.out.println("length must from 6 to 255");
            }
        } while (!Validate.isValidLength255(description));

        //author name
        do {
            System.out.println("Enter book author name: ");
            authorName = InputMethods.getString();
            if (!Validate.isValidLength50(authorName)) {
                System.out.println("length must from 2 to 50");
            }
        } while (!Validate.isValidLength50(authorName));

        //publisher name
        do {
            System.out.println("Enter book publisher name: ");
            publishersName = InputMethods.getString();
            if (!Validate.isValidLength50(publishersName)) {
                System.out.println("length must from 2 to 50");
            }
        } while (!Validate.isValidLength50(publishersName));

        //price
        do {
            System.out.println("Enter book price: ");
            price = InputMethods.getBigDecimal();
            if (!Validate.isPositiveNumber(price.toString())) {
                System.out.println("must positive number");
            }
        } while (!Validate.isPositiveNumber(price.toString()));

        //total pages
        do {
            System.out.println("Enter book total pages: ");
            totalPages = InputMethods.getInteger();
            if (!Validate.isPositiveNumber(totalPages+"")) {
                System.out.println("must positive number");
            }
        } while (!Validate.isPositiveNumber(totalPages+""));

        //status. default true
        bookStatus = true;
    }
}
