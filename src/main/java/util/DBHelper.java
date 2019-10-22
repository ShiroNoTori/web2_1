package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBHelper {

    public static Connection getMysqlConnection() {
        try {
            //com.mysql.cj.jdbc.Driver
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").
                    append("localhost:").
                    append("3306/").
                    append("db_example?").
                    append("user=root&").
                    append("password=1Vectorcontrol").
                    append("&serverTimezone=UTC");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }
}
