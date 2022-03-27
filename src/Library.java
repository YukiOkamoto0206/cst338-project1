import java.util.HashMap;
import java.util.List;

public class Library {
    public static int LENDING_LIMIT = 5;
    private String name;
    private static int libraryCard;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;
    private HashMap<Book, Integer> books;

    public Library(String library) {

    }
}
