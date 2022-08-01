package jm.task.core.jdbc.dao;

import com.mysql.cj.exceptions.UnableToConnectException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable()  {
        try(Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();
        ) {
            String sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT AUTO_INCREMENT, " +
                    " name VARCHAR(255) NOT NULL, " +
                    " lastName VARCHAR(255) NOT NULL, " +
                    " age TINYINT NOT NULL, " +
                    " PRIMARY KEY ( id ))";

            stmt.executeUpdate(sql);
            System.out.println("Таблица в базе данных успешно создана.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try(Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();
        ) {
            String sql = "DROP TABLE IF EXISTS users";
            stmt.executeUpdate(sql);
            System.out.println("Таблица из базы данных удалена.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();
        ) {
            String sql = "INSERT INTO users (name,lastName, age) Values (?,?,?);";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, lastName);
            pstmt.setByte(3,age);
            pstmt.executeUpdate();

            System.out.println("Пользователь " + name + " добавлен в базу.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        try(Connection conn = Util.getConnection(); ) {

            PreparedStatement  ps = conn.prepareStatement("DELETE FROM users WHERE id = ?");
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Пользователь удален по ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Пользователь не удален");
        }
    }

    public List<User> getAllUsers() {
        List <User> users = new ArrayList<>();
        try(Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();
        ) {
            String sql = "SELECT * FROM users";
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                 User user = new User();
                 user.setId(rs.getLong("id"));
                 user.setName(rs.getString("name"));
                 user.setLastName(rs.getString("lastName"));
                 user.setAge(rs.getByte("age"));
                 users.add(user);
            }
            System.out.println("Список всех данных из таблицы: " + users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Connection conn = Util.getConnection();
            Statement stmt = conn.createStatement();
        ) {
            String sql = "TRUNCATE userbd.users";
            stmt.executeUpdate(sql);
            System.out.println("Таблица очищена.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
