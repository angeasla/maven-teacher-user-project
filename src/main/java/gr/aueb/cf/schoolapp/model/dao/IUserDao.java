package gr.aueb.cf.schoolapp.model.dao;

import gr.aueb.cf.schoolapp.User;
import gr.aueb.cf.schoolapp.model.dao.exception.UserDaoException;

import java.util.List;

public interface IUserDao {

    User insert (User user) throws UserDaoException;
    User update (User user) throws UserDaoException;
    void delete (int id) throws UserDaoException;
    List<User> getByUsername(String username) throws UserDaoException;
    User getById(int id) throws UserDaoException;
}
