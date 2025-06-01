package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://pg.pg4e.com:5432/pg4e_7cfc5c1054?sslmode=require";
    private static final String USER = "pg4e_7cfc5c1054";
    private static final String PASSWORD = "pg4e_p_c788a74f96af495"; // from your .pgpass

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
