import java.util.ArrayList;
import java.util.Scanner;

public class Main extends FetchQuote {
    public static void main(String[] args) {

        showInstructions();

        Scanner scanner = new Scanner(System.in);
        String decision = "";
        ArrayList<String> possibleVariants = new ArrayList<>();
        possibleVariants.add("hello");
        possibleVariants.add("joke");
        possibleVariants.add("variants");
        possibleVariants.add("exit");
        possibleVariants.add("quote");
        possibleVariants.add("weather");

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

    private static void getQuoteOfTheDay(){

    }

    private static void getWeather(){

    }

    private static void exit() {
        System.out.println(ANSI_RED + "**************************");
        System.out.println("Exit!");
        System.out.println("**************************" + ANSI_RED);
    }
}