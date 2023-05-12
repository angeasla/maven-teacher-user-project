package gr.aueb.cf.schoolapp.model.service;

import gr.aueb.cf.schoolapp.User;
import gr.aueb.cf.schoolapp.model.dao.IUserDao;
import gr.aueb.cf.schoolapp.model.dao.exception.UserDaoException;
import gr.aueb.cf.schoolapp.model.dto.UserDTO;
import gr.aueb.cf.schoolapp.model.service.exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements IUserService {
    private final IUserDao userDao;

    public UserServiceImpl(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User insertUser(UserDTO userToInsert) throws UserDaoException {

        if (userToInsert == null) return null;

        try {
            User user = mapUser(userToInsert);

            return userDao.insert(user);
        } catch (UserDaoException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User updateUser(UserDTO userToUpdate) throws UserDaoException, UserNotFoundException {
        System.out.println("Check update service IN");


        if (userToUpdate == null) return null;

        try {
            if (userDao.getByUsername(userToUpdate.getUsername()) == null) {
                throw new UserNotFoundException("User with id" + userToUpdate.getId() + " not found");
            }

            User user = mapUser(userToUpdate);
            return userDao.update(user);
        } catch (UserDaoException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUser(int id) throws UserDaoException, UserNotFoundException {

        try {
            if (userDao.getById(id) == null) {
                throw new UserNotFoundException("User with id" + id + " not found");
            }
            userDao.delete(id);
        } catch (UserDaoException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<User> getUserByUsername(String username) throws UserDaoException{
        List<User> users = new ArrayList<>();
        if (username == null) return users;

        try {
            users = userDao.getByUsername(username);
            return users;
        } catch (UserDaoException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private User mapUser (UserDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }
}
