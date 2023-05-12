package gr.aueb.cf.schoolapp.model.service;

import gr.aueb.cf.schoolapp.User;
import gr.aueb.cf.schoolapp.model.dao.exception.UserDaoException;
import gr.aueb.cf.schoolapp.model.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {

    User insertUser (UserDTO userToInsert) throws UserDaoException;
    User updateUser (UserDTO userToUpdate) throws UserDaoException,UserNotFoundException;
    void deleteUser (int id) throws UserDaoException,UserNotFoundException;
    List<User> getUserByUsername (String username) throws UserDaoException;
}
