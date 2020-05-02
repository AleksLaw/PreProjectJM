package service;

import DAO.UserDAO;
import model.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService() {
        this.userDAO = UserDAO.getUserDAO();
    }

    public List<User> allUser() {
        ArrayList<User> list = null;
        try {
            list = (ArrayList<User>) userDAO.allUserDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        list.sort(Comparator.comparing(User::getId));
        return list;
    }

    public boolean addUser(User user) {
        boolean result = false;
        try {
            result = userDAO.addUserDAO(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean delUser(User user) {
        boolean result = false;
        try {
            result = userDAO.delUserDAO(user);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public boolean updateUser(User userOld, User userNew) {
        boolean result = false;
        try {
            result = userDAO.updateUserDAO(userOld, userNew);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    public void createTable() throws SQLException {
        userDAO.createTable();
    }

    public void dropTable() throws SQLException {
        userDAO.dropTable();
    }
}
