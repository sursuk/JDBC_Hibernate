package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    public void createUsersTable() {
        final String sql = "CREATE TABLE IF NOT EXISTS userTable ( " +
                "id  BIGINT NOT NULL AUTO_INCREMENT," +
                "name VARCHAR(45) NULL," +
                "lastName VARCHAR(45) NULL,"+
                "age TINYINT NULL," +
                "PRIMARY KEY (id));";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        final String sql = "DROP TABLE IF EXISTS userTable" ;

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        final String sql = "FROM User";

        Session session = Util.getSessionFactory().openSession();
        List<User> listUser = session.createQuery(sql).list();
        session.close();
        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        final String sql = "DELETE User";

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }
}
