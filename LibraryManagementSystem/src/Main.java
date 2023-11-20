import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

        ArrayList<Book<String, String, Integer>> books = new ArrayList<>();

        Book<String, String, Integer> newBook = getBook();
        books.add(newBook);

        for (Book<String, String, Integer> book : books) {
            System.out.println("-----------------------");
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor());
            System.out.println("Publishing Year: " + book.getPublishingYear());
            System.out.println("-----------------------");
        }
    }

    public static Book<String, String, Integer> getBook(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insert book title");
        String title = scanner.nextLine();
        System.out.println("Insert book author");
        String author = scanner.nextLine();
        System.out.println("Insert publishing year");
        int publishingYear = scanner.nextInt();
        return new Book<>(title, author, publishingYear);

    }
}