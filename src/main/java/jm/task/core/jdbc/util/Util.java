package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USERNAME_DB = "root";
    private static final String PASSWORD_DB = "1234";

    private static Connection connection;

    Util() {

    }

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME_DB, PASSWORD_DB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
