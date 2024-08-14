package by.edu.academy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.edu.academy.dao.СategoryDao;
import by.edu.academy.model.category.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class СategoryDaoImpl implements СategoryDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Category getCategoryById(int id) {
		return entityManager.find(Category.class, id);
	}

	@Override
	public List<Category> getAllCategories() {
		return entityManager.createQuery("SELECT c FROM Category c", Category.class).getResultList();
	}
}
