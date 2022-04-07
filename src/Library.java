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

//    private Code initBooks(int bookCount, Scanner scan) {
//        if (bookCount < 1) {
//            return Code.LIBRARY_ERROR;
//        } else {
//            for (int i = 0; i < bookCount; i++) {
//                String[] arr = scan.next().split(",");
//                int page = convertInt(arr[3], Code.PAGE_COUNT_ERROR);
//                new Book(arr[0], arr[1], arr[2], page, arr[4], arr[5]);
//
//            }
//        }
//        return Code.SUCCESS;
//    }
private Code initBooks(int bookCount, Scanner scan){

    String[] bookData;
    Book book;
    LocalDate date;
    int pageCount;

    if(bookCount < 1){
        return Code.LIBRARY_ERROR;
    }

    for(int i = 0; i < bookCount; i++){
        bookData = scan.nextLine().split(",");

        pageCount = convertInt(bookData[Book.PAGE_COUNT_], Code.PAGE_COUNT_ERROR);
        date = convertDate(bookData[Book.DUE_DATE_], Code.DATE_CONVERSION_ERROR);

        if(pageCount <= 0){
            return Code.PAGE_COUNT_ERROR;
        } else if(date == null) {
            return Code.DATE_CONVERSION_ERROR;
        } else {
            book = new Book(bookData[Book.ISBN_], bookData[Book.TITLE_], bookData[Book.SUBJECT_],
                    pageCount, bookData[Book.AUTHOR_], date);
            addBook(book);
        }
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

    public static LocalDate convertDate(String date, Code code) {
        LocalDate local;
        if (date.equals("0000")) {
            local = new LocalDate(1970, 1, 1);
            return local;
        }
        String[] arr = date.split("-");

    }

}
