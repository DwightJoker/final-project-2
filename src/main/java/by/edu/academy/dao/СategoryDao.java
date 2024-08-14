package by.edu.academy.dao;

import java.util.List;

import by.edu.academy.exception.DaoException;
import by.edu.academy.model.category.Category;

public interface СategoryDao {

	Category getCategoryById(int id) throws DaoException;

	List<Category> getAllCategories() throws DaoException;

}
