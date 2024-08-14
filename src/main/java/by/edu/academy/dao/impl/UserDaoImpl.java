package by.edu.academy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.edu.academy.dao.UserDao;
import by.edu.academy.exception.DaoException;
import by.edu.academy.model.AuthData;
import by.edu.academy.model.user.User;
import jakarta.persistence.NoResultException;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void registration(User user) throws DaoException {
		String hql = "FROM User WHERE login = :login";
		User userExist;
		try {
			userExist = sessionFactory.getCurrentSession().createQuery(hql, User.class)
					.setParameter("login", user.getLogin()).getSingleResult();
			if (userExist != null) {
				throw new DaoException("User with this username already exists");
			}
		} catch (NoResultException e) {
			sessionFactory.getCurrentSession().persist(user);
		}
	}

	@Override
	public User getUserByLogin(String login) throws DaoException {
		String sql = "FROM User WHERE login = :login";
		try {
			return sessionFactory.getCurrentSession().createQuery(sql, User.class).setParameter("login", login)
					.getSingleResult();
		} catch (Exception e) {
			throw new DaoException("User not found with login: " + login, e);
		}
	}

	@Override
	public List<User> getAllUsers() throws DaoException {
		try {
			return sessionFactory.getCurrentSession().createQuery("from User", User.class).getResultList();
		} catch (Exception e) {
			throw new DaoException("Failed to retrieve all users", e);
		}
	}

	@Override
	public User auth(AuthData authData) throws DaoException {
		String hql = "FROM User WHERE login = :login";
		try {
			User user = sessionFactory.getCurrentSession().createQuery(hql, User.class)
					.setParameter("login", authData.getLogin()).getSingleResult();

			if (user != null && user.getPassword().equals(authData.getPassword())) {
				return user;
			} else {
				throw new DaoException("Wrong login or password");
			}
		} catch (NoResultException e) {
			throw new DaoException("User not found", e);
		}
	}

	@Override
	public void deleteUser(int userId) throws DaoException {
		User user = getCurrentSession().get(User.class, userId);
		if (user != null) {
			getCurrentSession().remove(user);
		}
	}

}
