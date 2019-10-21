package service.dao;

import model.User;

import java.util.List;

public interface UserDao {

    User getById(int id);

    List<User> getAll();

    boolean remove(int id);

    boolean add(User user);

    boolean update(User user);
}