package by.edu.academy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.edu.academy.dao.UserDao;
import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.AuthData;
import by.edu.academy.model.user.User;
import by.edu.academy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public User auth(AuthData authData) throws ServiceException {
		try {
			return userDao.auth(authData);
		} catch (Exception e) {
			throw new ServiceException("Wrong login or password");
		}
	}

	@Transactional
	@Override
	public void registration(User user) throws ServiceException {
		try {
			userDao.registration(user);
		} catch (Exception e) {
			throw new ServiceException("User with this username already exists");
		}
	}

	@Transactional
	@Override
	public List<User> getAllUsers() throws ServiceException {
		try {
			return userDao.getAllUsers();
		} catch (Exception e) {
			throw new ServiceException("Users not found");
		}
	}

	@Transactional
	@Override
	public User getUserByLogin(String login) throws ServiceException {
		try {
			return userDao.getUserByLogin(login);
		} catch (Exception e) {
			throw new ServiceException("User not found with login: " + login, e);
		}
	}

	@Override
	@Transactional
	public void deleteUser(int userId) throws ServiceException {
		try {
			userDao.deleteUser(userId);
		} catch (Exception e) {
			throw new ServiceException("Failed to delete user", e);
		}
	}

}