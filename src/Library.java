import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Library {
    public static int LENDING_LIMIT = 5;
    private String name;
    private static int libraryCard;
    private List<Reader> readers;
    private HashMap<String, Shelf> shelves;
    private HashMap<Book, Integer> books;

    public Library(String library) {

    }

    public Code init(String filename) {
        File file = new File(filename);
        Code code = null;
        System.out.println(filename);
        System.out.println(file);
        try {
            System.out.println("00");
            Scanner scan = new Scanner(file);
            System.out.println(scan);
            System.out.println("11");
//            scan.useDelimiter(",");
            while (scan.hasNext()) {
                System.out.println(scan.nextLine());
            }
            System.out.println("22");
            scan.close();
            int count_books = convertInt(scan.nextLine(), code);
            if (count_books < 0) {
                System.out.println("count_books is less than 0");
                return Code.UNKNOWN_ERROR;
            } else {
                return initBooks(count_books, scan);
            }
        } catch (FileNotFoundException e) {
            return Code.FILE_NOT_FOUND_ERROR;
        }
    }

    private Code initBooks(int bookCount, Scanner scan) {
        if (bookCount < 1) {
            return Code.LIBRARY_ERROR;
        } else {
            for (int i = 0; i < bookCount; i++) {
                System.out.println(scan.next());

            }
        }
        return Code.SUCCESS;
    }

    public static int convertInt(String str, Code code) {
        int parseInt = Integer.parseInt(str);
        if (parseInt < 0) {
            return code.getCode();
        } else {
            return parseInt;
        }
    }
}
