import java.util.HashMap;
import java.util.Objects;

/**
 * Author:
 * Date: March 4 2022
 * Concrete:
 */
public class Shelf {
    public static final int SHELF_NUMBER_ = 0;
    public static final int SUBJECT_ = 0;
    private int shelfNumber;
    private String subject;
    private HashMap<Book, Integer> books;

    public Shelf(int shelfNumber, String subject) {
        this.shelfNumber = shelfNumber;
        this.subject = subject;
        this.books = new HashMap<>();
    }

    // addBook
    public Code addBook(Book book) {
        if (books.containsKey(book)) {
            // the count(Integer in Hashmap) should be incremented
            books.replace(book, books.get(book) + 1);
            System.out.println(book + " added to self");
            return Code.SUCCESS;
        } else {
            if (subject.equals(book.getSubject())) {
                books.put(book, 1);
                System.out.println(book + " added to self");
                return Code.SUCCESS;
            } else {
                return Code.SHELF_SUBJECT_MISMATCH_ERROR;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelf)) return false;
        Shelf shelf = (Shelf) o;
        return getShelfNumber() == shelf.getShelfNumber() && Objects.equals(getSubject(), shelf.getSubject());
    }

    // getBookCount
    public int getBookCount(Book book) {
        if (books.containsKey(book)) {
            return books.get(book);
        } else {
            return -1;
        }
    }

    public HashMap<Book, Integer> getBooks() {
        return books;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getShelfNumber(), getSubject());
    }

    // listBooks

    // removeBook
    public Code removeBook(Book book) {
        if (!books.containsKey(book)) {
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        } else {
            if (books.get(book) == 0) {
                return Code.BOOK_NOT_IN_INVENTORY_ERROR;
            } else {
                books.replace(book, books.get(book) - 1);
                return Code.SUCCESS;
            }
        }
    }

    public void setBooks(HashMap<Book, Integer> books) {
        this.books = books;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return shelfNumber + " : " + subject;
    }
}
