import java.util.List;
import java.util.Objects;

public class Reader {
    public int CARD_NUMBER_;
    public int NAME_;
    public int PHONE_;
    public int BOOK_COUNT_;
    public int BOOK_START_;

    private int cardNumber;
    private String name;
    private String phone;
    private List<Book> books;

    public Reader(int cardNumber, String name, String phone) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.phone = phone;
    }

    public Code addBook(Book book) {
        for (Book data : books) {
            if (data.equals(book)) {
                return Code.BOOK_ALREADY_CHECKED_OUT_ERROR;
            }
        }
        books.add(book);
        return Code.SUCCESS;
    }

    public Code removeBook(Book book) {
        for (Book data : books) {
            if (data.equals(book)) {
                books.remove(book);
                return Code.SUCCESS;
            }
        }
        return Code.READER_DOESNT_HAVE_BOOK_ERROR;
    }

    public boolean hasBook(Book book) {
        for (Book data : books) {
            if (data.equals(book)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reader)) return false;
        Reader reader = (Reader) o;
        return cardNumber == reader.cardNumber && Objects.equals(name, reader.name) && Objects.equals(phone, reader.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, name, phone);
    }

    @Override
    public String toString() {
        return name + " (#" + cardNumber + ") has checked out " + books;
    }
}
