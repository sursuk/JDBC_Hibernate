package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {

    }
    public void createUsersTable() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS usertable ( " +
                    "id  BIGINT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(45) NULL," +
                    "lastName VARCHAR(45) NULL,"+
                    "age TINYINT NULL," +
                    "PRIMARY KEY (id));").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            session.close();
        }
    }


    public void dropUsersTable() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS userTable").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }


    public void saveUser(String name, String lastName, byte age) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (RuntimeException e) {
            if( transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }


    public void removeUserById(long id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (RuntimeException e) {
            if( transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }


    public List<User> getAllUsers() {

        Session session = sessionFactory.openSession();
        try {
            List<User> listUser = session.createQuery("FROM User").list();
            return listUser;
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
    }


    public void cleanUsersTable() {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createQuery("DELETE User").executeUpdate();
            transaction.commit();
        } catch (RuntimeException e) {
            if( transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
