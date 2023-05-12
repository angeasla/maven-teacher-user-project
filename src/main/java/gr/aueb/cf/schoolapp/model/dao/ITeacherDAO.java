package gr.aueb.cf.schoolapp.model.dao;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.dao.exception.TeacherDAOExceptions;

import java.util.List;

public interface ITeacherDAO {

    Teacher insert(Teacher teacher) throws TeacherDAOExceptions;
    Teacher update(Teacher teacher) throws TeacherDAOExceptions;
    void delete (int id) throws TeacherDAOExceptions;
    List<Teacher> getByLastName(String lastname) throws TeacherDAOExceptions;
    Teacher getById(int id) throws TeacherDAOExceptions;
}
