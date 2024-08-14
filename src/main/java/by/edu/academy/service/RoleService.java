package by.edu.academy.service;

import java.util.List;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.role.Role;

public interface RoleService {

	Role getRoleById(int id) throws ServiceException;

	List<Role> getAllRoles() throws ServiceException;

}