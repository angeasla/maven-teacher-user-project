package gr.aueb.cf.schoolapp.model.service;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.model.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolapp.model.dao.dbutil.DBHelper;
import gr.aueb.cf.schoolapp.model.dao.exception.TeacherDAOExceptions;
import gr.aueb.cf.schoolapp.model.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.service.exceptions.TeacherNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TeacherServiceImplTest {

    private static ITeacherDAO teacherDAO = new TeacherDAOImpl();
    private static ITeacherService teacherService;

    @BeforeAll
    public static void setupClass() {
        teacherService = new TeacherServiceImpl(teacherDAO);
    }

    @BeforeEach
    public void setup() throws TeacherDAOExceptions {
        createDummyTeachers();
    }

    @AfterEach
    public void tearDown() throws SQLException {
        DBHelper.eraseData();
    }

    @Test
    public void insertAndGetTeacher() throws TeacherDAOExceptions, TeacherNotFoundException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Anna", "Giannoutsou");
        teacherService.insertTeacher(teacherDTO);

        List<Teacher> teachersList = teacherService.getTeacherByLastName(teacherDTO.getLastname());

        assertEquals(1,teachersList.size());
        assertEquals(teachersList.get(0).getLastname(),teacherDTO.getLastname());
        assertEquals(teachersList.get(0).getFirstname(),teacherDTO.getFirstname());
    }

    @Test
    public void update() throws TeacherDAOExceptions, TeacherNotFoundException {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Giannis2", "Iwannis2" );
        teacherService.updateTeacher(teacherDTO);

        List<Teacher> teachers = teacherService.getTeacherByLastName(teacherDTO.getLastname());
        assertEquals(teachers.get(0).getLastname(),teacherDTO.getLastname());
        assertEquals(teachers.get(0).getFirstname(),teacherDTO.getFirstname());
    }

    @Test
    public void updateInvalidTeacher() throws TeacherDAOExceptions, TeacherNotFoundException {
        TeacherDTO teacherDTO = new TeacherDTO(10, "Anna", "Giannoutsou");
        teacherService.updateTeacher(teacherDTO);

        assertThrows(TeacherNotFoundException.class,() -> {
            teacherService.updateTeacher(teacherDTO);
        });
    }

    @Test
    public void deleteTeacher() throws TeacherNotFoundException, TeacherDAOExceptions {
        TeacherDTO teacherDTO = new TeacherDTO(1, "Giannis", "Iwannis" );
        teacherService.deleteTeacher(teacherDTO.getId());

        List<Teacher> teachers = teacherService.getTeacherByLastName(teacherDTO.getLastname());
        assertEquals(0, teachers.size());
    }

    @Test
    public void deleteInvalidTeacher() throws TeacherDAOExceptions, TeacherNotFoundException {
        TeacherDTO teacherDTO = new TeacherDTO(10, "Giannis", "Iwannis");
        teacherService.deleteTeacher(teacherDTO.getId());

        assertThrows(TeacherNotFoundException.class,() -> {
            teacherService.deleteTeacher(teacherDTO.getId());
        });
    }

    public static void createDummyTeachers() throws TeacherDAOExceptions {
        Teacher teacher = new Teacher();
        teacher.setFirstname("Giannis");
        teacher.setLastname("Iwannis");
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