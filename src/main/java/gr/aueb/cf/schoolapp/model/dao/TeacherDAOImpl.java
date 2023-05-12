package gr.aueb.cf.schoolapp.model.dao;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.dao.exception.TeacherDAOExceptions;
import gr.aueb.cf.schoolapp.model.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAOImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOExceptions {
        System.out.println("Check insert DAO");
        String sql = "INSERT INTO TEACHERS (FIRSTNAME, LASTNAME) VALUES (?, ?) ";

        try (Connection conn = DBUtil.getConnection();
            PreparedStatement p = conn.prepareStatement(sql)) {

            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();

            if (firstname.equals("") | lastname.equals("")) {
                return null;
            }

            p.setString(1,firstname);
            p.setString(2,lastname);
            p.executeUpdate();
            return teacher;

        } catch (SQLException e) {
//            e.printStackTrace(); //logging
            throw new TeacherDAOExceptions("SQL Error In Teacher " + teacher + " insertion");
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOExceptions {
        System.out.println("Check update in DAO");

        String sql = "UPDATE TEACHERS SET FIRSTNAME = ?, LASTNAME = ? WHERE ID = ?";

        try(Connection conn = DBUtil.getConnection();
            PreparedStatement p = conn.prepareStatement(sql)) {

            String firstname = teacher.getFirstname();
            String lastname = teacher.getLastname();
            int id = teacher.getId();


            if (firstname.equals("") | lastname.equals("")) {
                return null;
            }

            p.setString(1,firstname);
            p.setString(2,lastname);
            p.setInt(3, id);
            p.executeUpdate();
            System.out.println("Check update out DAO");

            return teacher;

        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOExceptions("SQL Error In Teacher " + teacher + " update");
        }
    }

    @Override
    public void delete(int id) throws TeacherDAOExceptions {
        String sql = "DELETE FROM TEACHERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1,id);
            p.executeUpdate();

        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOExceptions("SQL Error In Teacher with ID= " + id + " deleted");

        }
    }

    @Override
    public List<Teacher> getByLastName(String lastname) throws TeacherDAOExceptions {
        String sql = "SELECT ID, FIRSTNAME, LASTNAME FROM TEACHERS WHERE LASTNAME LIKE ?";
        ResultSet rs;
        List<Teacher> teachers = new ArrayList<>();

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, lastname + '%');
            rs = p.executeQuery();

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME")
                );
                teachers.add(teacher);
            }

            return teachers;

        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOExceptions("SQL Error in Teacher with Lastname = " + lastname);
        }
    }

    @Override
    public Teacher getById(int id) throws TeacherDAOExceptions {
        Teacher teacher = null;
        ResultSet rs;
        String sql = "SELECT ID,FIRSTNAME,LASTNAME FROM TEACHERS WHERE ID = ? ";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1, id);
            rs = p.executeQuery();

            if (rs.next()) {
                teacher = new Teacher(
                        rs.getInt("ID"),
                        rs.getString("FIRSTNAME"),
                        rs.getString("LASTNAME"));
            }

            return teacher;

        } catch (SQLException e) {
//            e.printStackTrace();
            throw new TeacherDAOExceptions("SQL Error in Teacher with id = " + id);
        }
    }
}
