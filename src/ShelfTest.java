import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Random;

/**
 * Author: Yuki Okamoto
 * Date: March 6 2022
 * Concrete: ShelfTest file to check if Self.java works or not
 */

public class ShelfTest {
    public static final int SHELF_NUMBER_ = 0;
    public static final int SUBJECT_ = 0;
    int shelfNumber;
    String subject;
    HashMap<Book, Integer> books;

    @BeforeEach
    void setUp() {
        shelfNumber = 1001;
        subject = "education";
        books = new HashMap<>();
    }

    @Test
    void getBooks_test() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        Shelf shelf_education = new Shelf(shelfNumber, subject);
        shelf_education.addBook(book);
        books.put(book, 1);
        assertEquals(shelf_education.getBooks(), books);
    }

    @Test
    void getShelfNumber_test() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        Shelf shelf_education = new Shelf(shelfNumber, subject);
        shelf_education.addBook(book);
        assertEquals(shelf_education.getShelfNumber(), shelfNumber);
    }

    @Test
    void getSubject_test() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        Shelf shelf_education = new Shelf(shelfNumber, subject);
        shelf_education.addBook(book);
        assertEquals(shelf_education.getSubject(), subject);
    }

    @Test
    void setBooks_test() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        books.put(book, 1);
        Shelf shelf_education = new Shelf(shelfNumber, subject);
        shelf_education.setBooks(books);
        assertEquals(shelf_education.getBooks(), books);
    }

    @Test
    void setShelfNumber_test() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        Shelf shelf_education = new Shelf(shelfNumber, subject);
        assertNotEquals(shelf_education.getShelfNumber(),10);
        shelf_education.setShelfNumber(10);
        assertEquals(shelf_education.getShelfNumber(),10);
    }

    @Test
    void setSubject() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void addBook() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        Shelf shelf_education = new Shelf(shelfNumber, subject);
        assertEquals(shelf_education.addBook(book), Code.SUCCESS);
        assertEquals(shelf_education.getBookCount(book), 1);
        Book copy_book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        shelf_education.addBook(copy_book);
        assertEquals(shelf_education.getBookCount(book), 2);
        Book mismatch_book = new Book("1337", "Headfirst Java", "programming", 1337, "Grady Booch", LocalDate.now());
        assertEquals(shelf_education.addBook(mismatch_book), Code.SHELF_SUBJECT_MISMATCH_ERROR);
        assertNotEquals(shelf_education.getBookCount(book), 3);
    }

    @Test
    void removeBook() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        Shelf shelf_education = new Shelf(shelfNumber, subject);
        shelf_education.addBook(book);
        Book mismatch_book = new Book("1337", "Headfirst Java", "programming", 1337, "Grady Booch", LocalDate.now());
        assertEquals(shelf_education.removeBook(mismatch_book), Code.BOOK_NOT_IN_INVENTORY_ERROR);

        Book copy_book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        shelf_education.addBook(copy_book);
        shelf_education.addBook(copy_book);
        shelf_education.addBook(copy_book);
        assertEquals(shelf_education.getBookCount(book), 4);
        assertEquals(shelf_education.removeBook(book), Code.SUCCESS);
        assertEquals(shelf_education.getBookCount(book), 3);

        assertEquals(shelf_education.removeBook(copy_book), Code.SUCCESS);
        assertEquals(shelf_education.removeBook(copy_book), Code.SUCCESS);
        assertEquals(shelf_education.removeBook(copy_book), Code.SUCCESS);
        assertEquals(shelf_education.getBookCount(book), 0);
    }

    @Test
    void getBookCount() {
        Random random = new Random();
        int r = random.nextInt(10) + 1;
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        Shelf shelf_education = new Shelf(shelfNumber, subject);
        int i;
        for (i = 0; i < r; i++) {
            shelf_education.addBook(book);
        }
        assertEquals(shelf_education.getBookCount(book), i);

        shelf_education.removeBook(book);
        assertEquals(shelf_education.getBookCount(book), i - 1);

        for (int j = 0; j < i; j++) {
            shelf_education.removeBook(book);
        }
        assertEquals(shelf_education.getBookCount(book), 0);

        Book mismatch_book = new Book("1337", "Headfirst Java", "programming", 1337, "Grady Booch", LocalDate.now());
        assertEquals(shelf_education.getBookCount(mismatch_book), -1);
    }

    @Test
    void listBooks() {

    }

}
