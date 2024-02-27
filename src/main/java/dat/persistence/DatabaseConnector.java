package dat.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector
{
    private Connection connection;
    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;

    public DatabaseConnector(String URL, String USERNAME, String PASSWORD)
    {
        this.URL = URL;
        this.USERNAME = USERNAME;
        this.PASSWORD = PASSWORD;
    }

    public Connection getConnection() throws SQLException
    {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD );
        return connection;
    }
}
