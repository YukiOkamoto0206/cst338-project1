import java.io.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

public class Library {
    public static int LENDING_LIMIT = 5;
    private String name;
    private static int libraryCard = 0;
    private List<Reader> readers = new ArrayList<>();
    private HashMap<String, Shelf> shelves = new HashMap<>();
    private HashMap<Book, Integer> books = new HashMap<>();

    public Library(String name) {
        this.name = name;
    }

    public Code init(String filename) {
        Scanner scan = null;
        FileReader fr = null;
        Code currentCode;
        int bookCount;
        int shelfCount;
        int readerCount;

        try {
            fr = new FileReader(filename);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Could not read " + filename);
            return Code.FILE_NOT_FOUND_ERROR;
        }

        scan = new Scanner(fr);
        String current = scan.nextLine();

        // Convert the CSV to books.
        bookCount = convertInt(current, Code.BOOK_COUNT_ERROR);
        System.out.println("parsing " + bookCount + " books");

        if (bookCount < 0) {
            System.out.println("problem parsing books");
            return errorCode(bookCount);
        }

        currentCode = initBooks(bookCount, scan);
        System.out.println(currentCode);
        listBooks();

        current = scan.nextLine();

        shelfCount = convertInt(current, Code.SHELF_COUNT_ERROR);

        if (shelfCount < 0) {
            return errorCode(shelfCount);
        }

        System.out.println("parsing " + shelfCount + " shelves");
        currentCode = initShelves(shelfCount, scan);
        System.out.println(currentCode);

        listShelves(true);

        current = scan.nextLine();

        readerCount = convertInt(current, Code.READER_COUNT_ERROR);

        if (readerCount < 0) {
            return errorCode(readerCount);
        }

        currentCode = initReader(readerCount, scan);
        System.out.println(currentCode);

        return currentCode;
    }

    private Code initBooks(int bookCount, Scanner scan) {
        if (bookCount < 1) {
            System.out.println("Error: Number of Books doesn't make sense");
            return Code.LIBRARY_ERROR;
        }
        for (int i = 0; i < bookCount; i++) {
            String current = scan.nextLine();
            System.out.println("parsing book: " + current);

            String[] tokens = current.split(",");
            if (tokens.length < Book.DUE_DATE_) {
                return Code.BOOK_COUNT_ERROR;
            }

            String isbn = tokens[Book.ISBN_];
            String title = tokens[Book.TITLE_];

            int pageCount = convertInt(tokens[Book.PAGE_COUNT_], Code.PAGE_COUNT_ERROR);

            if (pageCount <= 0) {
                System.out.println("Error: parsing page count failed in initBooks");
                return Code.PAGE_COUNT_ERROR;
            }

            LocalDate dueDate = convertDate(tokens[Book.DUE_DATE_], Code.DUE_DATE_ERROR;
            if (dueDate == null) {
                System.out.println("Error: converting data failed in initBooks");
                return Code.DATE_CONVERSION_ERROR;
            }

            String author = tokens[Book.AUTHOR_];
            String subject = tokens[Book.SUBJECT_];
            Book book = new Book(isbn, title, subject, pageCount, author, dueDate);
            addBook(book);
        }

        return Code.SUCCESS;
    }

    private Code initShelves(int shelfCount, Scanner scan) {

    }

    public static int convertInt(String recordCountString, Code code) {
        int recordCount;
        try {
            recordCount = Integer.parseInt(recordCountString);
        } catch (NumberFormatException e) {
            System.out.println("Value which caused the error: " + recordCountString);
            System.out.println("Error message: " + code.getMessage());
            switch (code) {
                case BOOK_COUNT_ERROR:
                    System.out.println("Error: Could not read number of books");
                    break;
                case PAGE_COUNT_ERROR:
                    System.out.println("Error: Could not parse page count");
                    break;
                case DATE_CONVERSION_ERROR:
                    System.out.println("Error: Could not parse date component");
                default:
                    System.out.println("Error: Unknown conversion error");
            }
            return code.getCode();
        }
        return recordCount;
    }

    public static LocalDate convertDate(String date, Code errorCode) {
        if (date.equals("0000")) {
            return LocalDate.of(1970, 1, 1);
        }
        String[] parts = date.split("-");
        if (parts.length < 3) {
            System.out.println("Error: date conversion error, could not parse" + date);
            System.out.println("Using default date (01-jan-1970)");
            return LocalDate.of(1970, 1, 1);
        }
        int year = convertInt(parts[0], errorCode);
        int month = convertInt(parts[1], errorCode);
        int day = convertInt(parts[2], errorCode);

        if (year < 0 || month < 0 || day < 0) {
            System.out.println("Error converting date: Year: " + year + ")");
            System.out.println("Error converting date: Month: " + month + ")");
            System.out.println("Error converting date: Day: " + day + ")");
            System.out.println("Using default date (01-jan-1970)");
            return LocalDate.of(1970, 1, 1);
        }
        return LocalDate.of(year, month, day);
    }

}
