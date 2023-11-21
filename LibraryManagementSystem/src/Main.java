import java.util.Scanner;

public class Main {

    protected static Scanner scanner = new Scanner(System.in);
    protected static String path = "books.txt";
    protected static String decision = "";

    private enum Command {
        ADD,
        DELETE,
        CHANGE,
        COUNT,
        READALL,
        GETTITLE,
        GETAUTHOR,
        GETDATE,
        VARIANTS,
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
            case "change" -> Command.CHANGE;
            case "count" -> Command.COUNT;
            case "read all" -> Command.READALL;
            case "get titles" -> Command.GETTITLE;
            case "get authors" -> Command.GETAUTHOR;
            case "get dates" -> Command.GETDATE;
            case "variants" -> Command.VARIANTS;
            case "exit" -> Command.EXIT;
            default -> Command.INVALID;
        };
    }

    private static void processInput(Command command) {
        switch (command) {
            case ADD -> Library.addBooksToLibrary(path);
            case DELETE -> Library.deleteBookFromLibrary(path);
            case CHANGE -> Utils.changeBooksInfo(path);
            case COUNT -> Utils.countLines(path);
            case READALL -> Library.readFromFile(path);
            case GETTITLE, GETAUTHOR, GETDATE -> Utils.getParts(path, decision);
            case VARIANTS -> Utils.showVariants();
            case EXIT -> Utils.exit();
            case INVALID -> System.err.println("Invalid input!");
        }
    }

    private static void checkInput() {
        do {
            if (scanner.hasNextLine()) {
                decision = scanner.nextLine();
                Command command = parseInput(decision);
                processInput(command);
            } else {
                System.err.println("Incorrect input");
            }
        } while (!decision.equals("exit"));
    }
}

