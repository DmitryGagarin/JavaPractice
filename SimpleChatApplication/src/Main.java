import java.util.Scanner;

public class Main extends FetchWeather {

    public static Scanner scanner = new Scanner(System.in);
    public static String decision = "";

    public enum Command {
        HELLO,
        JOKE,
        VARIANTS,
        EXIT,
        QUOTE,
        INVALID
    }

    public static void main(String[] args) {

        showInstructions();

        checkInputAndShowResult();
    }

    private static Command parseInput(String input){
        return switch (input.toLowerCase()) {
            case "hello" -> Command.HELLO;
            case "joke" -> Command.JOKE;
            case "variants" -> Command.VARIANTS;
            case "exit" -> Command.EXIT;
            case "quote" -> Command.QUOTE;
            default -> Command.INVALID;
        };
    }
    private static void processCommand(Command command) {
        switch (command) {
            case HELLO -> getHello();
            case JOKE -> getJoke();
            case VARIANTS -> getVariants();
            case EXIT -> exit();
            case QUOTE -> getQuoteOfTheDay();
            case INVALID -> System.out.println("Invalid command. Please try again.");
        }
    }

    private static void checkInputAndShowResult() {
        do {
            if (scanner.hasNextLine()) {
                decision = scanner.nextLine();
                Command command = parseInput(decision);
                processCommand(command);
            } else {
                System.err.println("Incorrect input");
            }
        } while (!decision.equals("exit"));
    }

    private static void showInstructions() {
        System.out.println(ANSI_GREEN + "**************************");
        System.out.println("Welcome to Simple Chat App!");
        System.out.println("There are some instructions");
        getVariants();
    }

    private static void getVariants() {
        System.out.println(ANSI_GREEN + "**************************");
        System.out.println("If you want to say hello to you, input 'hello'");
        System.out.println("If you want to get a joke, input 'joke'");
        System.out.println("If you want to see variants, input 'variants'");
        System.out.println("If you want to see a quote, input 'quote'");
        System.out.println("If you want to exit, input 'exit'");
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void getHello() {
        System.out.println(ANSI_GREEN + "**************************");
        sayHello();
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void getJoke() {
        System.out.println(ANSI_BLUE + "**************************");
        fetchJoke();
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void getQuoteOfTheDay() {
        System.out.println(ANSI_BLUE + "**************************");
        fetchQuote();
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void exit() {
        System.out.println(ANSI_RED + "**************************");
        System.out.println("Exit!");
        System.out.println("**************************" + ANSI_RED);
    }
}