package factory;

import dao.UserDao;
import dao.impl.UserDaoImplHibernate;
import util.DBHelper;

public class UserDaoFactoryHibernate implements UserDaoFactory {

    @Override
    public UserDao createUserDao() {
        return new UserDaoImplHibernate(DBHelper.getInstance());
    }
}