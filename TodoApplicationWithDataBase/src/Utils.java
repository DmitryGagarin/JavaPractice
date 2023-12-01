import java.sql.Connection;
import java.sql.DriverManager;

public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    private static final String baseName = "root";
    private static final String basePass = "12213223Dfg";
    private static final String baseURL = "jdbc:mysql://127.0.0.1:3306/jdbc";
    private static Connection connection;

    protected static void showVariants() {
        System.out.println(ANSI_GREEN + "**********************");
        System.out.println("Add new task");
        System.out.println("List all");
        System.out.println("Count");
        System.out.println("Complete task");
        System.out.println("Variants");
        System.out.println("Exit");
        System.out.println("**********************" + ANSI_RESET);
    }

    protected static void exit() {
        System.out.println(ANSI_RED + "exit" + ANSI_RESET);
    }

    protected static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(baseURL, baseName, basePass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    protected static void showNumberOfElements() {
        int numberOfElements = Analytics.countElements();
        System.out.println(ANSI_CYAN + "There are: " + numberOfElements + " tasks to do" + ANSI_RESET);
    }
}
