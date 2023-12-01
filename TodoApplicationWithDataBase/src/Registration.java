import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Registration {
    private static final Connection connection = Utils.getConnection();

    protected static void addNewElement() {
        int numberOfElements = Analytics.countElements();
        System.out.println("Which task you want to add?");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        Utils.getConnection();
        try {
            Statement addNewPersonStatement = connection.createStatement();
            String addNewPerson = "insert into `jdbc`.`people` (`id`,`task`) values (" + (numberOfElements + 1) + ", '" + userName + "')";
            addNewPersonStatement.executeUpdate(addNewPerson);
            System.out.println(Utils.ANSI_GREEN + userName + " Was added to the todo list" + Utils.ANSI_RESET);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void deleteElement() {
        try {
            Statement statement = connection.createStatement();
            Scanner scanner = new Scanner(System.in);
            System.out.println("Insert number of task you want to delete");
            Analytics.listElements();
            int id = scanner.nextInt();
            String deleteTask = "delete from people where id = " + id + " ";
            statement.executeUpdate(deleteTask);
            Analytics.listElements();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
