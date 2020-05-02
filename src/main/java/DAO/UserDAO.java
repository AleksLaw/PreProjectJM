package DAO;


import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDAO {
    private Connection connection;

    private UserDAO(Connection connection) {
        this.connection = connection;
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());
            StringBuilder url = new StringBuilder();
            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("testdb?").          //db name
                    append("user=root&").          //login
                    append("password=root");       //password
            System.out.println("URL: " + url + "\n");
            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    public static UserDAO getUserDAO() {
        return new UserDAO(getMysqlConnection());
    }

    public List<User> allUserDAO() throws SQLException {
        List<User> list = new ArrayList<>();
        PreparedStatement prstm = connection.prepareStatement("select * from testdb.users");
        ResultSet result = prstm.executeQuery();
        while (result.next()) {
            Long tmpId = result.getLong(1);
            String tmpName = result.getString(2);
            String tmpPass = result.getString(3);
            User tmpUser = new User(tmpId, tmpName, tmpPass);
            list.add(tmpUser);
        }
        result.close();
        prstm.close();
        return list;
    }

    public int count() throws SQLException {
        Statement statement = connection.
                createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sql = "select * from testdb.users";
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.last();
        int i = resultSet.getRow();
        resultSet.close();
        statement.close();
        return i;
    }

    public boolean addUserDAO(User user) throws SQLException {
        int beforeAdd = count();
        PreparedStatement prstm = connection.prepareStatement(
                "insert into testdb.users (name, surname)  values (?,?)");
        prstm.setString(1, user.getName());
        prstm.setString(2, user.getSurname());
        prstm.executeUpdate();
        prstm.close();
        int afterAdd = count();
        return beforeAdd <= afterAdd;
    }

    public boolean delUserDAO(User user) throws SQLException {
        int beforeDel = count();
        PreparedStatement prstm = connection.prepareStatement(
                "select * from testdb.users where name = ? and surname = ?",
                ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
        prstm.setString(1, user.getName());
        prstm.setString(2, user.getSurname());
        ResultSet result = prstm.executeQuery();
        result.next();
        result.deleteRow();
        result.close();
        prstm.close();
        int afterDel = count();
        return beforeDel > afterDel;
    }

    public boolean updateUserDAO(User userOld, User userNew) throws SQLException {
        int beforeUpdate = count();
        PreparedStatement prstm = connection.prepareStatement(
                "UPDATE  testdb.users SET name=?, surname=? WHERE id = ?;");
        long id = getUserIdByName(userOld.getName(), userOld.getSurname());
        prstm.setString(1, userNew.getName());
        prstm.setString(2, userNew.getSurname());
        prstm.setLong(3, id);
        int afterUpdate = count();
        prstm.executeUpdate();
        prstm.close();
        return beforeUpdate == afterUpdate;
    }

    public long getUserIdByName(String name, String surname) throws SQLException {
        PreparedStatement prstm = connection.prepareStatement(
                "SELECT *  FROM testdb.users where name=? and surname=? ;");
        prstm.setString(1, name);
        prstm.setString(2, surname);
        ResultSet result = prstm.executeQuery();
        result.next();
        long id = result.getLong(1);
        result.close();
        prstm.close();
        return id;
    }


    public void createTable() throws SQLException {
        PreparedStatement prstm = connection.prepareStatement
                (
                        "create table if not exists users " +
                                "(id bigint auto_increment, name varchar(256)" +
                                ", surname varchar(256), primary key (id))"
                );
        prstm.executeUpdate();
        prstm.close();
    }

    public void dropTable() throws SQLException {
        PreparedStatement prstm = connection.prepareStatement(
                "DROP TABLE IF EXISTS users");
        prstm.executeUpdate();
        prstm.close();
    }
}
