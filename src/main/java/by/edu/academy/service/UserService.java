package by.edu.academy.service;

import java.util.List;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.AuthData;
import by.edu.academy.model.user.User;

public interface UserService {

	User getUserByLogin(String login) throws ServiceException;

	void registration(User user) throws ServiceException;

	List<User> getAllUsers() throws ServiceException;

	User auth(AuthData authData) throws ServiceException;

	void deleteUser(int userId) throws ServiceException;

}