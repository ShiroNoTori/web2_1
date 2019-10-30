package factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class UserDaoFactoryProducer {

    public static UserDaoFactory getFactory(String route) {
        String dao = parseProperty(route);

        try {
            Class<?> clazz = Class.forName(dao);
            return (UserDaoFactory) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        /*switch (dao) {
            case "factory.UserDaoFactoryJDBC": {
                return new UserDaoFactoryJDBC();
            }
            case "factory.UserDaoFactoryHibernate": {
                return new UserDaoFactoryHibernate();
            }
            default: {
                throw new RuntimeException("DAO factory not found.");
            }
        }*/
    }

    private static String parseProperty(String route) {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String dao = "";

        try (InputStream in = classloader.getResourceAsStream(route)) {
            Properties prop = new Properties();
            prop.load(in);
            dao = prop.getProperty("dao");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dao;
    }
}