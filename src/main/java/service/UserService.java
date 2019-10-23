package service;

import dao.UserDao;
import dao.impl.UserDaoImplHibernate;
import dao.impl.UserDaoImplJDBC;
import model.User;
import util.DBHelper;

import java.util.List;

public class UserService {

    private static UserService instance;

    private UserDao getUserDAO() {
        return new UserDaoImplJDBC(DBHelper.getMysqlConnection());
        //return new UserDaoImplHibernate(DBHelper.getSessionFactory().openSession());
    }

    private UserService() {
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public User getById(int id) {
        return getUserDAO().getById(id);
    }

    public List<User> getAll() {
        return getUserDAO().getAll();
    }

    public boolean remove(int id) {
        return getUserDAO().remove(id);
    }

    public boolean add(User user) {
        return getUserDAO().add(user);
    }

    public boolean update(User user) {
        return getUserDAO().update(user);
    }

    public boolean hasId(int id) {
        return getUserDAO().hasId(id);
    }
}