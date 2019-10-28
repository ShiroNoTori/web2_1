package factory;

import dao.UserDao;
import dao.impl.UserDaoImplJDBC;
import util.DBHelper;

public class UserDaoFactoryJDBC implements UserDaoFactory {

    @Override
    public UserDao createUserDao() {
        return new UserDaoImplJDBC(DBHelper.getInstance());
    }
}