package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static Logger LOGGER = Logger.getLogger(Util.class.getName());
    private static final  String url = "jdbc:mysql://localhost:3306/userbd";
    private static final String username = "root";
    private static final String password = "123123";

    public static  Connection getConnection()  {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "Нет соединения с базой данных.");
        }
        return conn;

    }
}