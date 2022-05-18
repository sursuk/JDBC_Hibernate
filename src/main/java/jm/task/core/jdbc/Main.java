package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();

        userDao.saveUser("1", "11", (byte)1);
        userDao.saveUser("2", "22", (byte)2);
        userDao.saveUser("3", "33", (byte)3);
        userDao.saveUser("4", "44", (byte)4);

        System.out.println(userDao.getAllUsers());

        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
