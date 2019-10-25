package dao.factory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactoryProducer {

    public static UserDaoFactory getFactory(String route) {
        String dao = parseProperty(route);

        switch (dao) {
            case "jdbc": {
                return new UserDaoFactoryJDBC();
            }
            case "hibernate": {
                return new UserDaoFactoryHibernate();
            }
            default: {
                throw new RuntimeException("DAO factory not found.");
            }
        }
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