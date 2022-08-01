package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final  String url = "jdbc:mysql://localhost:3306/userbd";
    private static final String username = "root";
    private static final String password = "123123";

    public static  Connection getConnection()  {
        Connection conn;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
           throw new IllegalStateException("Нет соединения с базой данных!", e);
        }
        return conn;

    }
}