package gr.aueb.cf.schoolapp.model.service;

import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.model.dao.exception.TeacherDAOExceptions;
import gr.aueb.cf.schoolapp.model.dto.TeacherDTO;
import gr.aueb.cf.schoolapp.model.service.exceptions.TeacherNotFoundException;

import java.util.List;

public interface ITeacherService {

    Teacher insertTeacher (TeacherDTO teacherToInsert) throws TeacherDAOExceptions;
    Teacher updateTeacher (TeacherDTO teacherToUpdate) throws TeacherDAOExceptions, TeacherNotFoundException;
    void deleteTeacher (int id) throws TeacherDAOExceptions, TeacherNotFoundException;
    List<Teacher> getTeacherByLastName(String lastname) throws TeacherDAOExceptions, TeacherNotFoundException;

}
