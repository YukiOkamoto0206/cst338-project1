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

    public Code addBook(Book newBook) {
        if (books.containsKey(newBook)) {
            int count = books.get(newBook);
            count++;
            books.put(newBook, count);
            System.out.println(books.get(newBook) + " copies of " + newBook + " in the stack");
        } else {
            books.put(newBook, 1);
            System.out.println(newBook + " added to the stacks.");
        }

        for (Shelf shelf : shelves.values()) {
            if (shelf.getSubject().equalsIgnoreCase(newBook.getSubject())) ;
            shelf.addBook(newBook);
            return Code.SUCCESS;
        }
        System.out.println("No shelf for " + newBook.getSubject() + " books");
        return Code.SHELF_EXISTS_ERROR;
    }

    private Code initShelves(int shelfCount, Scanner scan) {
        if (shelfCount < 1) {
            System.out.println("Error: Number of shelves doesn't make sense [initShelves]");
            return Code.SHELF_COUNT_ERROR;
        }
        Shelf shelf = null;

        for (int i = 1; i <= shelfCount; i++) {
            String current = scan.nextLine();
            System.out.println("Parsing shelf : " + current);
            String[] tokens = current.split(",");

            int shelfNumber = convertInt(tokens[Shelf.SHELF_NUMBER_], Code.SHELF_NUMBER_PARSE_ERROR);

            if (shelfNumber < 0) {
                return errorCode(shelfNumber);
            }

            String subject = tokens[Shelf.SUBJECT_];

            shelf = new Shelf(shelfNumber, subject);

            addShelf(shelf);
        }
        if (shelves.size() == shelfCount) {
            return Code.SUCCESS;
        } else {
            System.out.println("number of shelves doesn't match expected");
            return Code.SHELF_NUMBER_PARSE_ERROR;
        }
    }

    public Code addShelf(String shelfSubject) {
        Shelf shelf = new Shelf(shelves.size() + 1, shelfSubject);
        return addShelf(shelf);
    }

    public Code addShelf(Shelf shelf) {
        if (shelves.containsKey(shelf.getSubject())) {
            System.out.println("Error: shelf already exists " + shelf);
            return Code.SHELF_EXISTS_ERROR;
        }
        int maxNumber = 0;
        for (Shelf s : shelves.values()) {
            if (maxNumber > s.getShelfNumber()) {
                maxNumber = s.getShelfNumber() + 1;
            }
        }

        if (shelf.getShelfNumber() < maxNumber) {
            shelf.setShelfNumber(maxNumber);
        }

        shelves.put(shelf.getSubject(), shelf);

        for (Book book : books.keySet()) {
            if (book.getSubject().equalsIgnoreCase(shelf.getSubject())) {
                for (int i = 0; i < books.get(book); i++) {
                    shelf.addBook(book);
                }
            }
        }
        return Code.SUCCESS;
    }

    private Code initReader(int readerCount, Scanner scan) {
        if (readerCount <= 0) {
            System.out.println("ERROR: ReaderCount must be greater than 0 [initReader]");
            return Code.READER_COUNT_ERROR;
        }

        for (int j = 0; j < readerCount; j++) {
            String current = scan.nextLine();
            String[] tokens = current.split(",");

            int cardNumber = convertInt(tokens[Reader.CARD_NUMBER_], Code.READER_CARD_NUMBER_ERROR);
            if (cardNumber > libraryCard) {
                libraryCard = cardNumber + 1;
            }
            String name = tokens[Reader.NAME_];
            String phoneNumber = tokens[Reader.PHONE_];

            Reader reader = new Reader(cardNumber, name, phoneNumber);

            readers.add(reader);

            int bookCount = convertInt(tokens[Reader.BOOK_COUNT_], Code.BOOK_COUNT_ERROR);
            int records = tokens.length - bookCount;

            for (int i = Reader.BOOK_START_; i < tokens.length; i++) {
                Book book = this.getBookByISBN(tokens[i]);
                i++;
                if (book == null) {
                    System.out.println("ERROR!");
                    continue;
                }
                LocalDate dueDdate = convertDate(tokens[i], Code.DUE_DATE_ERROR);
                book.setDueDate(dueDdate);
                System.out.println(this.checkOutBook(reader, book));
            }
        }
        return Code.SUCCESS;
    }

    public Code returnBook(Reader reader, Book book) {
        if (!reader.hasBook(book)) {
            System.out.println(reader.getName() + " Doesn't have " + book + " Checked out");
            return Code.READER_DOESNT_HAVE_BOOK_ERROR;
        }

        if (!books.containsKey(book)) {
            System.out.println(book + " does not belong to this library");
            return Code.BOOK_NOT_IN_INVENTORY_ERROR;
        }

        System.out.println(reader.getName() + " is returning " + book);
        Code code = reader.removeBook(book);

        if (code.equals(Code.SUCCESS)) {
            code = returnBook(book);
        } else {
            System.out.println("Could not return " + book);
        }

        return code;
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

    public static int getLibraryCardNumber() {
        return libraryCard + 1;
    }

    private Code errorCode(int codeNumber) {
        for (Code code : Code.values()) {
            if (code.getCode() == codeNumber) {
                return code;
            }
        }
        return Code.UNKNOWN_ERROR;
    }

}
