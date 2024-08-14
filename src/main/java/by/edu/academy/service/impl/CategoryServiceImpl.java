package by.edu.academy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.edu.academy.dao.СategoryDao;
import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.category.Category;
import by.edu.academy.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private СategoryDao categoryDao;

	@Transactional(readOnly = true)
	@Override
	public Category getCategoryById(int id) throws ServiceException {
		try {
			return categoryDao.getCategoryById(id);
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve category with ID " + id, e);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<Category> getAllCategories() throws ServiceException {
		try {
			return categoryDao.getAllCategories();
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve categories", e);
		}
	}
}