import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void constructor() {
        Book book = null;
        assertNull(book);
        Book book1 = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertNotNull(book1);
    }

    @Test
    void getIsbn() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertEquals("1337", book.getIsbn());
    }

    @Test
    void setIsbn() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        String isbn = "1000";
        assertNotEquals(isbn, book.getIsbn());
        book.setIsbn(isbn);
        assertEquals(isbn, book.getIsbn());
    }

    @Test
    void getTitle() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertEquals("Headfirst Java", book.getTitle());
    }

    @Test
    void setTitle() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        String title = "Headfirst C++";
        assertNotEquals(title, book.getTitle());
        book.setTitle(title);
        assertEquals(title, book.getTitle());
    }

    @Test
    void getSubject() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertEquals("education", book.getSubject());
    }

    @Test
    void setSubject() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        String subject = "sci-fi";
        assertNotEquals(subject, book.getSubject());
        book.setSubject(subject);
        assertEquals(subject, book.getSubject());
    }

    @Test
    void getPageCount() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertEquals(1337, book.getPageCount());
    }

    @Test
    void setPageCount() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        int pageCount = 100;
        assertNotEquals(pageCount, book.getPageCount());
        book.setPageCount(pageCount);
        assertEquals(pageCount, book.getPageCount());
    }

    @Test
    void getAuthor() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertEquals("Grady Booch", book.getAuthor());
    }

    @Test
    void setAuthor() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        String author = "Alexandre Dumas";
        assertNotEquals(author, book.getAuthor());
        book.setAuthor(author);
        assertEquals(author, book.getAuthor());
    }

    @Test
    void getDueDate() {
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertEquals(LocalDate.now(), book.getDueDate());
    }

    @Test
    void setDueDate() {
        LocalDate date = LocalDate.of(2000, 1,1);
        Book book = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        assertNotEquals(date, book.getDueDate());
        book.setDueDate(date);
        assertEquals(date, book.getDueDate());
    }

    @Test
    void testEquals() {
        Book book1 = new Book("1337", "Headfirst Java", "education", 1337, "Grady Booch", LocalDate.now());
        Book book2 = new Book("42-w-87", "Hitchhikers Guide To the Galaxy", "sci-fi", 42, "Douglas Adams", LocalDate.now());
        assertNotEquals(book1, book2);
        Book boo3 = book1;
        assertEquals(book1, boo3);
    }
}