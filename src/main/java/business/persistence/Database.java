package business.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database
{
    private Connection connection;
    private final String USER;
    private final String PASSWORD;
    private final String URL;

    public Database(String user, String password, String url) throws ClassNotFoundException
    {
        String deployed = System.getenv("DEPLOYED");
        if (deployed != null)
        {
            // Prod: hent variabler fra setenv.sh i Tomcats bin folder
            USER = System.getenv("JDBC_USER");
            PASSWORD = System.getenv("JDBC_PASSWORD");
            URL = System.getenv("JDBC_CONNECTION_STRING");
        } else
        {
            USER = "root";
            PASSWORD = "Datamatiker123";
            URL = "jdbc:mysql://localhost:3306/fog?serverTimezone=CET&useSSL=false";
        }
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    public Connection connect() throws SQLException
    {
        Connection connection = null;
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        return connection;
    }
}
