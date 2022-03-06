import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;
import java.util.HashMap;

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
//        Shelf shelf_education = new Shelf(1001, "education");
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        books = new HashMap<>();
        books.put(book, 1);
    }

    @Test
    void getShelfNumber_test() {
    }

    @Test
    void getSubject_test() {
    }

    @Test
    void setBooks() {
    }

    @Test
    void setShelfNumber() {
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
    }

    @Test
    void getBookCount() {
    }

    @Test
    void listBooks() {
    }

}
