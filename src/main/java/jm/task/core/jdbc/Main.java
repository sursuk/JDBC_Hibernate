package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

        userService.createUsersTable();

        userService.saveUser("1", "11", (byte)1);
        userService.saveUser("2", "22", (byte)2);
        userService.saveUser("3", "33", (byte)3);
        userService.saveUser("4", "44", (byte)4);

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
