package by.edu.academy.dao;

import java.util.List;

import by.edu.academy.exception.DaoException;
import by.edu.academy.model.role.Role;

public interface RoleDao {

	Role getRoleById(int id) throws DaoException;

	List<Role> getAllRoles() throws DaoException;

}
