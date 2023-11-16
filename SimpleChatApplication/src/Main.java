import java.util.Scanner;

public class Main extends FetchJoke {
    public static void main(String[] args) {

        //String joke = fetchJoke();
        showInstructions();

        Scanner scanner = new Scanner(System.in);
        String decision = "";

        do {
            if (scanner.hasNextLine()) {
                decision = scanner.nextLine();
                if (decision.equals("hello") || decision.equals("joke") || decision.equals("variants") || decision.equals("exit")) {
                    switch (decision) {
                        case "hello" -> callHello();
                        case "joke" -> sayJoke();
                        case "variants" -> showVariants();
                        case "exit" -> exit();
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
        System.out.println("If you want to say hello to you, input 'hello'");
        System.out.println("If you want to get a joke, input 'joke'");
        System.out.println("If you want to see variants, input 'variants'");
        System.out.println("If you want to exit, input 'exit'");
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void showVariants() {
        System.out.println(ANSI_GREEN + "**************************");
        System.out.println("If you want to say hello to you, input 'hello'");
        System.out.println("If you want to get a joke, input 'joke'");
        System.out.println("If you want to see variants, input 'variants'");
        System.out.println("If you want to exit, input 'exit'");
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void callHello() {
        System.out.println(ANSI_GREEN + "**************************");
        sayHello();
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void sayJoke() {
        System.out.println(ANSI_GREEN + "**************************");
        System.out.println("Joke!");
        System.out.println("**************************" + ANSI_RESET);
    }

    private static void exit() {
        System.out.println(ANSI_RED + "**************************");
        System.out.println("Exit!");
        System.out.println("**************************" + ANSI_RED);
    }
}