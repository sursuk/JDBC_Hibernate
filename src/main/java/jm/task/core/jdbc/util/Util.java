package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/new_schema";
    private static final String USERNAME_DB = "root";
    private static final String PASSWORD_DB = "1234";

    static Configuration configuration = new Configuration()
            .setProperty(Environment.URL, URL)
            .setProperty(Environment.USER, USERNAME_DB)
            .setProperty(Environment.PASS, PASSWORD_DB)
            .addAnnotatedClass(User.class);

    Util() {
    }

    public static SessionFactory getSessionFactory() {


        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static Connection getConnection() {
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME_DB, PASSWORD_DB);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
