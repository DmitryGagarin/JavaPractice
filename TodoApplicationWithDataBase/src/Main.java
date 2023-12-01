import java.util.Scanner;

public class Main {

    private enum Command {
        ADD_NEW_TASK,
        LIST_TASKS,
        COUNT,
        MARK_COMPLETED,
        VARIANTS,
        INVALID,
        EXIT
    }

    public static void main(String[] args) {
        Utils.getConnection();
        Utils.showVariants();
        checkInput();
    }

    private static Command parseInput(String input) {
        return switch (input.toLowerCase()) {
            case "add new task" -> Command.ADD_NEW_TASK;
            case "list all" -> Command.LIST_TASKS;
            case "count" -> Command.COUNT;
            case "complete task" -> Command.MARK_COMPLETED;
            case "variants" -> Command.VARIANTS;
            case "exit" -> Command.EXIT;
            default -> Command.INVALID;
        };
    }

    private static void processInput(Command command) {
        switch (command) {
            case ADD_NEW_TASK -> Registration.addNewElement();
            case COUNT -> Utils.showNumberOfElements();
            case LIST_TASKS -> Analytics.listElements();
            case MARK_COMPLETED -> Registration.deleteElement();
            case VARIANTS -> Utils.showVariants();
            case EXIT -> Utils.exit();
            case INVALID -> System.err.println("Invalid input!");
        }
    }

    private static void checkInput() {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        do {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
                Command command = parseInput(input);
                processInput(command);
            } else {
                System.err.println("Incorrect input");
            }
        } while (!input.equals("exit"));
    }
}
