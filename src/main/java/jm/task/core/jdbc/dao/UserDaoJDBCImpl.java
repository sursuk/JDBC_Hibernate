package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sql = "CREATE TABLE `new_schema`.`usertable` ( " +
                "`id`  BIGINT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NULL," +
                "`lastName` VARCHAR(45) NULL,"+
                "`age` TINYINT NULL," +
                "PRIMARY KEY (`id`));" ;


        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void dropUsersTable() {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DROP TABLE new_schema.userTable")) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO new_schema.userTable (name, lastName, age) VALUES (?, ?, ?) ";
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем –  " + name + " добавлен в базу данных");
        }  catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        String sql = "DELETE FROM usertable WHERE id = " + id;
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }  catch (SQLException e) {
        }
    }

    public List<User> getAllUsers() {

        ArrayList<User> resultList = new ArrayList<>();
        try (Statement statement = Util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM new_schema.usertable");
            while (resultSet.next()) {
                User user = new User(resultSet.getString(2), resultSet.getString(3), resultSet.getByte(4));
                resultList.add(user);
                System.out.println(resultList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement("DELETE FROM new_schema.usertable ")) {
            preparedStatement.executeUpdate();
        }  catch (SQLException e) {
        }
    }
}
