package by.edu.academy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.edu.academy.dao.RoleDao;
import by.edu.academy.model.role.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class RoleDaoImpl implements RoleDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Role getRoleById(int id) {
		return entityManager.find(Role.class, id);
	}

	@Override
	public List<Role> getAllRoles() {
		return entityManager.createQuery("SELECT r FROM Role r", Role.class).getResultList();
	}
}
