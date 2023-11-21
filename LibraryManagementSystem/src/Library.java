import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    protected static ArrayList<Book<String, String, Integer>> books = new ArrayList<>();

    private static ArrayList<Book<String, String, Integer>> getBooks() {
        return books;
    }

    private void setBooks(ArrayList<Book<String, String, Integer>> books) {
        Library.books = books;
    }

    public Library() {
    }

    protected static void addBook(Book<String, String, Integer> book) {
        books.add(book);
    }

    protected static void displayBooks() {
        System.out.println("-----------------------");
        System.out.println("Book added");
        for (Book<String, String, Integer> book : books) {
            System.out.println("-----------------------");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Publishing Year: " + book.getPublishingYear());
            System.out.println("-----------------------");
        }
    }

    protected static Book<String, String, Integer> createBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert book title");
        String title = scanner.nextLine();
        System.out.println("Insert book author");
        String author = scanner.nextLine();
        System.out.println("Insert publishing year");
        int publishingYear = scanner.nextInt();
        addBook(new Book<> (title, author, publishingYear));
        return new Book<>(title, author, publishingYear);
    }

    protected static void addToFile(String path) {
        try {
            Book<String, String, Integer> newBook = createBook();
            BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
            writer
                    .append("Title: ")
                    .append(newBook.getTitle())
                    .append(", Author: ")
                    .append(newBook.getAuthor())
                    .append(", Publishing year: ")
                    .append(newBook.getPublishingYear().toString())
                    .append(",\n");

            writer.close();
        } catch (IOException e) {
            System.out.println("Wrong file path");
            e.printStackTrace();
        }
    }

    protected static void readFromFile(String path) {
        try {
            List<String> db = Files.readAllLines(Paths.get(path));
            System.out.println("-----------------------");
            for (String line : db) {
                System.out.println(line);
            }
            System.out.println("-----------------------");
        } catch (IOException e) {
            System.out.println("Wrong file path");
            e.printStackTrace();
        }
    }
}
