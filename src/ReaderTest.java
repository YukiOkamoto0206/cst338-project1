import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReaderTest {

    int cardNumber;
    String name;
    String phone;
    List<Book> books;
    Reader reader;

    @BeforeEach
    void setUp() {
        cardNumber = 20;
        name = "Yuki";
        phone = "8311010101";
        reader = new Reader(cardNumber, name, phone);
    }

    @AfterEach
    void tearDown() {
        reader = null;
    }

    @Test
    void addBook_test() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertEquals(reader.addBook(book), Code.SUCCESS);
        assertNotEquals(reader.addBook(book), Code.SUCCESS);
        assertEquals(reader.addBook(book), Code.BOOK_ALREADY_CHECKED_OUT_ERROR);
    }

    @Test
    void getBookCount_test() {
        assertEquals(0, reader.getBookCount());
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        reader.addBook(book);
        assertEquals(1, reader.getBookCount());
        reader.removeBook(book);
        assertEquals(0, reader.getBookCount());
//        Book book1 = new Book("42-w-87", "Hitchhikers", "sci-fi", 42, "Douglas Adams", LocalDate.now());
//        reader.addBook(book1);
//        assertEquals(2, reader.getBookCount());
    }

    @Test
    void hasBook_test() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertFalse(reader.hasBook(book));
        reader.addBook(book);
        assertTrue(reader.hasBook(book));
    }

    @Test
    void removeBook_test() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertEquals(reader.removeBook(book), Code.READER_DOESNT_HAVE_BOOK_ERROR);
        reader.addBook(book);
        assertEquals(reader.removeBook(book), Code.SUCCESS);
    }

    @Test
    void getCardNumber() {
        assertEquals(cardNumber, reader.getCardNumber());
    }

    @Test
    void setCardNumber() {
        assertEquals(cardNumber, reader.getCardNumber());
        reader.setCardNumber(30);
        assertNotEquals(cardNumber, reader.getCardNumber());
    }

    @Test
    void getName() {
        assertEquals(name, reader.getName());
    }

    @Test
    void setName() {
        assertEquals(name, reader.getName());
        reader.setName("Miguel");
        assertNotEquals(name, reader.getName());
    }

    @Test
    void getPhone() {
        assertEquals(phone, reader.getPhone());
    }

    @Test
    void setPhone() {
        assertEquals(phone, reader.getPhone());
        reader.setPhone("0101010101010");
        assertNotEquals(phone, reader.getPhone());
    }

    @Test
    void getBooks() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        books = new ArrayList<>();
        books.add(book);
        reader.addBook(book);
        assertEquals(books, reader.getBooks());
    }

    @Test
    void setBooks() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        books = new ArrayList<>();
        books.add(book);
        reader.setBooks(books);
        assertEquals(books, reader.getBooks());
    }
}