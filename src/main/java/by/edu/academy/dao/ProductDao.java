package by.edu.academy.dao;

import java.util.List;

import by.edu.academy.exception.DaoException;
import by.edu.academy.model.product.Product;

public interface ProductDao {

	void addProduct(Product product) throws DaoException;

	List<Product> getAllProducts() throws DaoException;

	void deleteProduct(int productId) throws DaoException;

	void updateProduct(Product product) throws DaoException;

	List<Product> getProductsByCategory(int categoryId) throws DaoException;

	Product getProductById(int productId) throws DaoException;
}
