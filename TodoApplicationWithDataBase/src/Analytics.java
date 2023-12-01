import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Analytics {
    private static final Connection connection = Utils.getConnection();
    protected static int numberOfElements = 0;
    static String selectAll = "select * from people";
    static String countElements = "select count(*) as total from people";

    protected static int countElements() {
        try {
            Statement showNumberOfPeopleStatement = connection.createStatement();
            ResultSet resultSetOfElements = showNumberOfPeopleStatement.executeQuery(countElements);
            if (resultSetOfElements.next()) {
                numberOfElements = resultSetOfElements.getInt("total");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return numberOfElements;
    }

    protected static void listElements() {
        try {
            Statement showPeopleStatement = connection.createStatement();
            ResultSet resultSetOfPeople = showPeopleStatement.executeQuery(selectAll);
            int orderNumber = 0;

            if (!resultSetOfPeople.next()) {
                System.out.println("There are no tasks to complete. Relax!");
            } else {
                do {
                    orderNumber++;
                    System.out.println(orderNumber + ") " + resultSetOfPeople.getString("task"));
                } while (resultSetOfPeople.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
