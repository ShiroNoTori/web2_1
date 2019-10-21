package service;

import model.User;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import util.JDBCUtil;

import java.util.List;

public class UserService {

    private UserDao userDao;

    public User getById(int id) {
        return new UserDaoImpl(JDBCUtil.getEntityManagerFactory().createEntityManager()).getById(id);
    }

    public List<User> getAll() {
        return new UserDaoImpl(JDBCUtil.getEntityManagerFactory().createEntityManager()).getAll();
    }

    public boolean remove(int id) {
        return new UserDaoImpl(JDBCUtil.getEntityManagerFactory().createEntityManager()).remove(id);
    }

    public boolean add(User user) {
        return new UserDaoImpl(JDBCUtil.getEntityManagerFactory().createEntityManager()).add(user);
    }

    public boolean update(User user) {
        return new UserDaoImpl(JDBCUtil.getEntityManagerFactory().createEntityManager()).update(user);
    }
}