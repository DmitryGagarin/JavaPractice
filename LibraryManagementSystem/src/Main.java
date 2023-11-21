import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Main {

    protected static Scanner scanner = new Scanner(System.in);
    protected static Library library = new Library();
    protected static String path = "books.txt";
    protected static String decision = "";

    private enum Command {
        ADD,
        DELETE,
        READALL,
        GETTITLE,
        GETAUTHOR,
        GETDATE,
        EXIT,
        INVALID
    }

    public static void main(String[] args) {

        Utils.showVariants();

        checkInput();

    }

    private static Command parseInput(String input) {
        return switch (input.toLowerCase()) {
            case "add" -> Command.ADD;
            case "delete" -> Command.DELETE;
            case "read all" -> Command.READALL;
            case "get titles" -> Command.GETTITLE;
            case "get authors" -> Command.GETAUTHOR;
            case "get dates" -> Command.GETDATE;
            case "exit" -> Command.EXIT;
            default -> Command.INVALID;
        };
    }

    private static void processInput(Command command) {
        switch (command) {
            case ADD -> addBooksToLibrary(library, path);
            case DELETE -> deleteBookFromLibrary(library, path);
            case READALL -> read(library, path);
            case GETTITLE, GETAUTHOR, GETDATE -> getParts(path);
            case EXIT -> Utils.exit();
            case INVALID -> System.out.println("Invalid input!");
        }
    }

    private static void checkInput() {
        do {
            if (scanner.hasNextLine()) {
                decision = scanner.nextLine();
                Command command = parseInput(decision);
                processInput(command);
            } else {
                System.out.println("Incorrect input");
            }
        } while (!decision.equals("exit"));
    }

    private static void addBooksToLibrary(Library library, String path) {
        System.out.println("How many books you want to add?");
        int numberOfBook = scanner.nextInt();
        for (int i = 0; i < numberOfBook; i++) {
            Library.addToFile(path);
        }
        Library.displayBooks();
    }

    private static void deleteBookFromLibrary(Library library, String path){
        int lines = countLines(path);
        System.out.println("Which book you want to delete? There are: " + lines + " books");
        StringBuilder result = new StringBuilder();
        try {
            List<String> db = Files.readAllLines(Paths.get(path));
            for (String line : db) {
                result.append(line);
            }
        } catch (IOException e){
            e.printStackTrace();
        }


        List<String> titles = Utils.extractTitles(String.valueOf(result), "Title");
        System.out.println("-----------------------");
        for (String title : titles){
            System.out.println(title);
        }
        System.out.println("-----------------------");
    }

    private static int countLines(String path){
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader((path)));
            while(reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static void read(Library library, String path) {
        Library.readFromFile(path);
    }

    private static void getParts(String path) {
        String filter;

        try {
            StringBuilder result = new StringBuilder();
            List<String> db = Files.readAllLines(Paths.get(path));
            for (String line : db) {
               result.append(line);
            }

            switch (decision){
                case "get titles" -> {
                    filter = "Title";
                    List<String> titles = Utils.extractTitles(String.valueOf(result), filter);
                    System.out.println("-----------------------");
                    for (String title : titles){
                        System.out.println(title);
                    }
                    System.out.println("-----------------------");

                }
                case "get authors" -> {
                    filter = "Author";
                    List<String> authors = Utils.extractTitles(String.valueOf(result), filter);
                    System.out.println("-----------------------");
                    for (String author : authors){
                        System.out.println(author);
                    }
                    System.out.println("-----------------------");

                }
                case "get dates" -> {
                    filter = "Publishing year";
                    List<String> dates = Utils.extractTitles(String.valueOf(result), filter);
                    System.out.println("-----------------------");
                    for (String date : dates){
                        System.out.println(date);
                    }
                    System.out.println("-----------------------");

                }
                default -> System.out.println("Something went wrong");
            }
        } catch (IOException e) {
            System.out.println("Wrong file path");
            e.printStackTrace();
        }
    }
}

