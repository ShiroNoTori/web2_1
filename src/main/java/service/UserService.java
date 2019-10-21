package service;

import model.User;
import dao.impl.UserDaoImpl;
import org.hibernate.SessionFactory;
import util.DBHelper;

import java.util.List;

public class UserService {

    private static UserService instance;

    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance() {
        if(instance == null) {
            instance = new UserService(DBHelper.getSessionFactory());
        }
        return instance;
    }

    public User getById(int id) {
        return new UserDaoImpl(sessionFactory.openSession()).getById(id);
    }

    public List<User> getAll() {
        return new UserDaoImpl(sessionFactory.openSession()).getAll();
    }

    public boolean remove(int id) {
        return new UserDaoImpl(sessionFactory.openSession()).remove(id);
    }

    public boolean add(User user) {
        return new UserDaoImpl(sessionFactory.openSession()).add(user);
    }

    public boolean update(User user) {
        return new UserDaoImpl(sessionFactory.openSession()).update(user);
    }

    public boolean hasId(int id) {
        return new UserDaoImpl(sessionFactory.openSession()).hasId(id);
    }
}