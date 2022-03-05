import java.util.HashMap;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shelf)) return false;
        Shelf shelf = (Shelf) o;
        return getShelfNumber() == shelf.getShelfNumber() && Objects.equals(getSubject(), shelf.getSubject());
    }

    // getBookCount

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
