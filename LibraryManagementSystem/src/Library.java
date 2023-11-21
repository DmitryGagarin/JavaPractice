import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Library {

    protected static Scanner scanner = new Scanner(System.in);

    private static ArrayList<Book<String, String, Integer>> books = new ArrayList<>();

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

    protected static void displayCreatedBook() {
        System.out.println(Utils.ANSI_GREEN + "-----------------------");
        System.out.println("Book added");
        for (Book<String, String, Integer> book : books) {
            System.out.println(Utils.ANSI_YELLOW + "-----------------------");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Publishing Year: " + book.getPublishingYear());
            System.out.println("-----------------------" + Utils.ANSI_RESET);
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
        addBook(new Book<>(title, author, publishingYear));
        return new Book<>(title, author, publishingYear);
    }

    protected static void addBooksToLibrary(String path) {
        System.out.println("How many books you want to add?");
        int numberOfBook = scanner.nextInt();
        for (int i = 0; i < numberOfBook; i++) {
            addToFile(path);
        }
        displayCreatedBook();
    }

    protected static void deleteBookFromLibrary(String path) {
        int lines = Utils.countLines(path);

        System.out.println("Which book you want to delete? There are: " + lines + " books");
        readFromFile(path);

        int lineToDelete = scanner.nextInt();

        String tempPath = "temp.txt";
        try {
            File inputFile = new File(path);
            File tempFile = new File(tempPath);
            BufferedReader reader = new BufferedReader(new FileReader(path));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempPath));

            String currentLine;
            int lineNumber = 1;

            while ((currentLine = reader.readLine()) != null) {
                if (lineNumber != lineToDelete) {
                    writer.write(currentLine + System.getProperty("line.separator"));
                }
                lineNumber++;
            }
            writer.close();
            reader.close();

            if (!inputFile.delete()) {
                System.out.println("Could not delete the original file");
                return;
            }

            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename the temporary file");
            }

            System.out.println(Utils.ANSI_RED + "Book " + lineToDelete + " has been deleted." + Utils.ANSI_RESET);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    protected static void changeBookTitle(String path) {
        // I have to find a book title that I need and change it to new one
    }

    protected static void changeBookAuthor(String path) {
        // I have to find an author that I need and change it to new one
    }

    protected static void changeBookPublishingYear(String path) {
        // I have to find a publishing year that I need and change it to new one
    }
}
