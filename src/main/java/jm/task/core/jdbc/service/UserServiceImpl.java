package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDaoJDBCImpl  ud = new UserDaoJDBCImpl();

    public void createUsersTable() {
            this.ud.createUsersTable();

    }

    public void dropUsersTable() {
        this.ud.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        this.ud.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        this.ud.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return  this.ud.getAllUsers();
    }

    public void cleanUsersTable() {
        this.ud.cleanUsersTable();
    }

}
