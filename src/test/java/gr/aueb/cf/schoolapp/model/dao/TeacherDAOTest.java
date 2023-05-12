package gr.aueb.cf.schoolapp.model.dao;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.model.dao.exception.TeacherDAOExceptions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherDAOTest {

    private static ITeacherDAO teacherDAO;

    @BeforeAll
    public static void setupClass() throws SQLException {
        teacherDAO = new TeacherDAOImpl();
        DBHelper.eraseData();
    }

    @BeforeEach
    void setUp() throws TeacherDAOExceptions {
        createDummyTeachers();
    }

    @AfterEach
    void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    void persistAndGetTeacher() throws TeacherDAOExceptions {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Bob");
        teacher.setLastname("Dylan");
        teacherDAO.insert(teacher);

        List<Teacher> teachers = teacherDAO.getByLastName("Dylan");
        assertEquals(1, teachers.size());
//        assertTrue(teachers.size() == 1);
    }

    @Test
    void update() throws TeacherDAOExceptions  {
       Teacher teacher = new Teacher();
        teacher.setId(2);
        teacher.setFirstname("Anna2");
        teacher.setLastname("Giannoutsou2");
        teacherDAO.update(teacher);

        List<Teacher> teachers = teacherDAO.getByLastName(teacher.getLastname());
        assertEquals(teachers.get(0).getLastname(), "Giannoutsou2");
    }

    @Test
    void delete() throws TeacherDAOExceptions {
        int id = 1;
        teacherDAO.delete(id);

        Teacher teacher = teacherDAO.getById(1);
        assertNull(teacher);
    }

    @Test
    void getByLastName() throws TeacherDAOExceptions {
        List<Teacher> teachers = teacherDAO.getByLastName("Androu");
        assertEquals(1,teachers.size());
    }

    @Test
    void getById() throws TeacherDAOExceptions {
        int id = 4;
        Teacher teacher = teacherDAO.getById(4);
        assertEquals("Lalakis",teacher.getLastname());
    }

    public static void createDummyTeachers() throws TeacherDAOExceptions {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Giannis");
        teacher.setLastname("Iwannis");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Anna");
        teacher.setLastname("Giannoutsou");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Kwstas");
        teacher.setLastname("Kwstakis");
        teacherDAO.insert(teacher);

        teacher = new Teacher();
        teacher.setFirstname("Lakis");
        teacher.setLastname("Lalakis");
        teacherDAO.insert(teacher);
    }


}