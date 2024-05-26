import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBFunctions {

    protected Connection connectToDb(String dbName, String password, String username) {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbName, username, password);
            if (connection != null) {
                System.out.println("Connection Established");
            } else {
                System.err.println("Connection not Established");
            }
        } catch (Exception exception) {
            System.err.println(exception);
        }
        return connection;
    }

    protected void createTable(Connection connection, String tableName) {
        Statement statement;
        try {
            String query =
                    "CREATE TABLE " + tableName +
                    "(employeeId SERIAL, " +
                    "name VARCHAR (200), " +
                    "address VARCHAR (200), " +
                    "PRIMARY KEY(employeeId));";
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(statement);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    protected void insertRow(Connection connection, String tableName, String name, String address) {
        Statement statement;
        try {
            String query = String.format("INSERT INTO %s(name, address) VALUES ('%s', '%s');", tableName, name, address);
            statement = connection.createStatement();
            statement.executeUpdate(query);
            System.out.println(statement);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    protected void readRow(Connection connection, String tableName) {
        Statement statement;
        ResultSet resultSet = null;
        try {
            String query = String.format("SELECT * FROM %s", tableName);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                System.out.print(resultSet.getString("employeeId") + "    ");
                System.out.print(resultSet.getString("name") + "    ");
                System.out.println(resultSet.getString("address") + "    ");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
