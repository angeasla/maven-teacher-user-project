package gr.aueb.cf.schoolapp.model.dao;

import gr.aueb.cf.schoolapp.User;
import gr.aueb.cf.schoolapp.model.dao.exception.UserDaoException;
import gr.aueb.cf.schoolapp.model.service.util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    @Override
    public User insert(User user) throws UserDaoException {
        String sql = "INSERT INTO USERS (USERNAME,PASSWORD) VALUES (?,?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String username = user.getUsername();
            String password = user.getPassword();
            String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());

            if (username.equals("") | hashedPassword.equals("")) {
                return null;
            }
            p.setString(1, username);
            p.setString(2, hashedPassword);
            p.executeUpdate();
            return user;
        } catch (SQLException e) {
            throw new UserDaoException("SQL Error in User" + user + " insertion");
        }
    }

    @Override
    public User update(User user) throws UserDaoException {
        System.out.println("Check update Dao IN");
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String username = user.getUsername();
            String password = user.getPassword();
            int id = user.getId();

            if (username.equals("") | password.equals("")) {
                return null;
            }
            p.setString(1, username);
            p.setString(2, password);
            p.setInt(3, id);
            p.executeUpdate();
            return user;

        } catch (SQLException e) {
            throw new UserDaoException("SQL Error in User" + user + " update");
        }
    }

    @Override
    public void delete(int id) throws UserDaoException {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setInt(1, id);
            p.executeUpdate();

        } catch (SQLException e) {
            throw new UserDaoException("SQL Error In User with ID= " + id + " deleted");
        }
    }
    @Override
    public List<User> getByUsername(String username) throws UserDaoException {
        String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME LIKE ?";
        ResultSet rs;
        List<User> users = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {
            p.setString(1, username + '%');
            rs = p.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );
                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            throw new UserDaoException("SQL Error in User with Username = " + username);
        }
    }

    @Override
    public User getById(int id) throws UserDaoException {
        System.out.println("Check getbyid Dao IN");

        User user = null;
        ResultSet rs;
        String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1,id);
            rs = p.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD")
                );

            }
            System.out.println("Check getById Dao OUT");

            return user;
        } catch (SQLException e) {
            throw new UserDaoException("SQL Error in Teacher with id = " + id);
        }
    }
}
