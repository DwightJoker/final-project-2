package by.edu.academy.dao;

import java.util.List;

import by.edu.academy.exception.DaoException;
import by.edu.academy.model.AuthData;
import by.edu.academy.model.user.User;

public interface UserDao {

	User getUserByLogin(String login) throws DaoException;

	void registration(User user) throws DaoException;

	List<User> getAllUsers() throws DaoException;

	User auth(AuthData authData) throws DaoException;

	void deleteUser(int userId) throws DaoException;

}
