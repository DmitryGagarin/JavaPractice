import java.util.Scanner;

public class Main {

    protected static Scanner scanner = new Scanner(System.in);
    protected static Library library = new Library();
    protected static String path = "books.txt";
    protected static String decision = "";

    private enum Command {
        ADD,
        READALL,
        GETTITLE,
        GETAUTHOR,
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
            case "read all" -> Command.READALL;
            case "get title" -> Command.GETTITLE;
            case "get author" -> Command.GETAUTHOR;
            case "exit" -> Command.EXIT;
            default -> Command.INVALID;
        };
    }

    private static void processInput(Command command) {
        switch (command) {
            case ADD -> addBooksToLibrary(library, path);
            case READALL -> read(library, path, scanner);
            case GETTITLE -> getTitle();
            case GETAUTHOR -> getAuthor();
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

    private static void read(Library library, String path, Scanner scanner) {
        boolean toRead = scanner.nextLine().equals("read");
        if (toRead) {
            Library.readFromFile(path);
        }
    }

    private static void getTitle() {

    }

    private static void getAuthor() {

    }
}

