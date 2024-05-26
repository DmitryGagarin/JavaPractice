import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        DBFunctions functions = new DBFunctions();

        Connection connection = functions.connectToDb("testDB", "", "postgres");
        //functions.createTable(connection, "employee");
        functions.insertRow(connection, "employee", "Arnold", "Baker Street, 123");
        functions.insertRow(connection, "employee", "Jam", "Baker Street, 124");
        functions.insertRow(connection, "employee", "Rolph", "Baker Street, 125");

        functions.readRow(connection, "employee");
    }
}