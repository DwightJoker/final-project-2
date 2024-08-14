package by.edu.academy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.edu.academy.dao.RoleDao;
import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.role.Role;
import by.edu.academy.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleDao roleDao;

	@Transactional(readOnly = true)
	@Override
	public Role getRoleById(int id) throws ServiceException {
		try {
			return roleDao.getRoleById(id);
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve role by ID", e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Role> getAllRoles() throws ServiceException {
		try {
			return roleDao.getAllRoles();
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve all roles", e);
		}
	}
}