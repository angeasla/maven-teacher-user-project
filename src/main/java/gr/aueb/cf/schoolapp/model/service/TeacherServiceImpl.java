package gr.aueb.cf.schoolapp.model.service;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.model.dao.exception.TeacherDAOExceptions;
import gr.aueb.cf.schoolapp.model.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.service.exceptions.TeacherNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class TeacherServiceImpl implements ITeacherService {
    private final ITeacherDAO teacherDAO;

    public TeacherServiceImpl(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher insertTeacher(TeacherDTO teacherToInsert) throws TeacherDAOExceptions {
        System.out.println("Check insert Service");
        if (teacherToInsert == null) return null;

        try {
            Teacher teacher = mapTeacher(teacherToInsert);
            return teacherDAO.insert(teacher);
        } catch (TeacherDAOExceptions e) {
//            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public Teacher updateTeacher(TeacherDTO teacherToUpdate) throws TeacherDAOExceptions, TeacherNotFoundException {
        System.out.println("Update teacher service");
        if (teacherToUpdate == null) return null;

        try {

            if (teacherDAO.getById(teacherToUpdate.getId()) == null) {
                throw new TeacherNotFoundException("Teacher with id " + teacherToUpdate.getId() + " not found");
            }

            Teacher teacher = mapTeacher(teacherToUpdate);
            System.out.println("Check update out Service");

            return teacherDAO.update(teacher);

        } catch (TeacherDAOExceptions | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteTeacher(int id) throws TeacherDAOExceptions, TeacherNotFoundException {
        try {

            if (teacherDAO.getById(id) == null) {
                throw new TeacherNotFoundException("Teacher with id " + id + " not found");
            }
            teacherDAO.delete(id);

        } catch (TeacherDAOExceptions | TeacherNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<Teacher> getTeacherByLastName(String lastname) throws TeacherDAOExceptions, TeacherNotFoundException {
        List<Teacher> teachers = new ArrayList<>();
        if (lastname == null) return teachers;

        try {
             teachers = teacherDAO.getByLastName(lastname);
//            if (teachers.size() == 0) throw new TeacherNotFoundException("Not teacher found with lastname starting with " + lastname);
            return teachers;
        } catch (TeacherDAOExceptions e) {
//            e.printStackTrace();
            throw e;
        }
    }


    private Teacher mapTeacher (TeacherDTO dto) {
        return new Teacher(dto.getId(), dto.getFirstname(), dto.getLastname());
    }
}
