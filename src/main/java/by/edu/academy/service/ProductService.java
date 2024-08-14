package by.edu.academy.service;

import java.util.List;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.product.Product;

public interface ProductService {

	void addProduct(Product product) throws ServiceException;

	List<Product> getAllProducts() throws ServiceException;

	void deleteProduct(int productId) throws ServiceException;

	void editProduct(Product product) throws ServiceException;

	List<Product> getProductByCategory(int categoryId) throws ServiceException;

	Product getProductById(int productId) throws ServiceException;

}