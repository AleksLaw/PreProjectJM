package service;

import DAO.UserDAO;
import model.User;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserService {
    private static UserService userService;
    private UserDAO userDAO;

    public static UserService getInstance() {
        if (userService == null) {
            userService = new UserService(UserDAO.getInstance());
        }
        return userService;
    }

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> allUser() {
        ArrayList<User> list = (ArrayList<User>) userDAO.allUserDAO();
        list.sort(Comparator.comparing(User::getId));
        return list;
    }

    public boolean addUser(User user) {
       return userDAO.addUserDAO(user);
    }

    public boolean delUser(User user) {
        return userDAO.delUserDAO(user);
    }

    public boolean updateUser(User userOld, User userNew) {
        return userDAO.updateUserDAO(userOld,userNew);
    }

    public Long findUser(User user) {
        return userDAO.findUserDAO(user);
    }

    public User getUserById(Long id) {
        return userDAO.getUserByIdDAO(id);
    }

}
