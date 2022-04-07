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
        String line = "";
        File file = new File(filename);
        Scanner scan;

        try {
            scan = new Scanner(file);
        } catch (FileNotFoundException e) {
            return Code.FILE_NOT_FOUND_ERROR;
        }

        if (scan.hasNextLine()) {
            line = scan.nextLine().trim();
            int count_books = convertInt(line, Code.BOOK_COUNT_ERROR);
            if (count_books < 0) {
                return Code.UNKNOWN_ERROR;
            }
            initBooks(count_books, scan);

            line = scan.nextLine().trim();
            int count_shelves = convertInt(line, Code.SHELF_COUNT_ERROR);
            if (count_shelves < 0) {
                return Code.UNKNOWN_ERROR;
            }
            initShelves(count_shelves, scan);
            listShelves(true);

            line = scan.nextLine().trim();
            int count_records = convertInt(line, Code.SHELF_COUNT_ERROR);
            if (count_records < 0) {
                return Code.UNKNOWN_ERROR;
            }
            initReader(count_records, scan);
            listReaders();
        }
        return Code.SUCCESS;
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

    public static int convertInt(String str, Code code) {
        int parseInt = Integer.parseInt(str);
        if (parseInt < 0) {
            return code.getCode();
        } else {
            return parseInt;
        }
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
