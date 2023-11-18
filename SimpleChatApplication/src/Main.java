import java.util.ArrayList;
import java.util.Scanner;

public class Main extends FetchWeather {

    public static Scanner scanner = new Scanner(System.in);
    public static String decision = "";
    public static ArrayList<String> possibleVariants = new ArrayList<>();

    public static void main(String[] args) {

        possibleVariants.add("hello");
        possibleVariants.add("joke");
        possibleVariants.add("variants");
        possibleVariants.add("exit");
        possibleVariants.add("quote");
        possibleVariants.add("weather");

        showInstructions();

        checkInputAndShowResult();
    }

    private static void checkInputAndShowResult() {
        do {
            if (scanner.hasNextLine()) {
                decision = scanner.nextLine();
                if (possibleVariants.contains(decision)) {
                    switch (decision) {
                        case "hello" -> getHello();
                        case "joke" -> getJoke();
                        case "variants" -> getVariants();
                        case "exit" -> exit();
                        case "quote" -> getQuoteOfTheDay();
                        case "weather" -> getWeather();
                        default -> System.out.println("Type mistake");
                    }
                } else {
                    if (decision.isEmpty()) {
                        System.err.println("Your input is empty");
                    } else {
                        System.err.println("Check your input (spelling)");
                    }
                }
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
        System.out.println("If you want to check a weather, input 'weather'");
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

    private static void getWeather() {
        System.out.println(ANSI_BLUE + "**************************");
        fetchWeather();
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void exit() {
        System.out.println(ANSI_RED + "**************************");
        System.out.println("Exit!");
        System.out.println("**************************" + ANSI_RED);
    }
}