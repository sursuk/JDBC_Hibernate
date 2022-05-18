package jm.task.core.jdbc.util;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    // реализуйте настройку соеденения с БД

    private static final String url = "jdbc:mysql://localhost:3306/";
    private static final String usernameDB = "root";
    private static final String passwordDB = "1234";

    private static Connection connection;

    Util() {

    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, usernameDB, passwordDB);
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException |
                 ClassNotFoundException |
                 SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
