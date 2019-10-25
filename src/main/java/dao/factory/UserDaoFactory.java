package dao.factory;

import dao.UserDao;

public interface UserDaoFactory {

    UserDao createUserDao();
}