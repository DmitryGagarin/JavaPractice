import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Utils {
    private static String baseName = "root";
    private static String basePass = "12213223Dfg";
    private static String baseURL = "jdbc:mysql://127.0.0.1:3306/jdbc";
    private static int numberOfElements = 0;
    private static String userName = "";
    private static Connection connection;

    static String selectAll = "select * from people";
    static String countElements = "select count(*) as total from people";

    private static void getConnection() {
        try {
            connection = DriverManager.getConnection(baseURL, baseName, basePass);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected static void countElements(){
        try {
            Statement showNumberOfPeopleStatement = connection.createStatement();
            ResultSet resultSetOfElements = showNumberOfPeopleStatement.executeQuery(countElements);
            if (resultSetOfElements.next()) {
                numberOfElements = resultSetOfElements.getInt("total");
                System.out.println("total elements: " + numberOfElements);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected static void listElements(){
        try {
            Statement showPeopleStatement = connection.createStatement();
            ResultSet resultSetOfPeople = showPeopleStatement.executeQuery(selectAll);
            while (resultSetOfPeople.next()) {
                System.out.println(resultSetOfPeople.getString("firstName"));
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected static void addNewElement() {
        try {
            Statement addNewPersonStatement = connection.createStatement();
            String addNewPerson = "insert into `jdbc`.`people` (`id`,`firstName`) values (" + (numberOfElements + 1) + ", 'vadim')";
            addNewPersonStatement.executeUpdate(addNewPerson);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    protected static void run() {
        getConnection();
        try {
            countElements();
            listElements();
            addNewElement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
