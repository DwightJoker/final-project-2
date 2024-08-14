package by.edu.academy.service;

import java.util.List;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.category.Category;

public interface CategoryService {

	Category getCategoryById(int id) throws ServiceException;

	List<Category> getAllCategories() throws ServiceException;

}