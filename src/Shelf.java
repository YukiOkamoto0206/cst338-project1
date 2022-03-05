import java.util.HashMap;

public class Shelf {
    public static final int SHELF_NUMBER_=0;
    public static final int SUBJECT_=0;
    private int shelfNumber;
    private String subject;
    private HashMap<Book, Integer> books;

    public Shelf(int shelfNumber, String subject) {
        this.shelfNumber = shelfNumber;
        this.subject = subject;
        this.books = new HashMap<>();
    }



}
