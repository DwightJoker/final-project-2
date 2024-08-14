package by.edu.academy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.edu.academy.dao.ProductDao;

import by.edu.academy.exception.ServiceException;

import by.edu.academy.model.product.Product;
import by.edu.academy.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;

	@Transactional
	@Override
	public void addProduct(Product product) throws ServiceException {
		try {
			productDao.addProduct(product);
		} catch (Exception e) {
			throw new ServiceException("Failed to add product", e);
		}
	}

	@Transactional
	@Override
	public List<Product> getAllProducts() throws ServiceException {
		try {
			List<Product> products = productDao.getAllProducts();
			System.out.println("Products retrieved: " + products);
			return products;
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve products", e);
		}
	}

	@Transactional
	@Override
	public void deleteProduct(int productId) throws ServiceException {
		try {
			productDao.deleteProduct(productId);
		} catch (Exception e) {
			throw new ServiceException("Failed to delete product", e);
		}
	}

	@Transactional
	@Override
	public void editProduct(Product product) throws ServiceException {
		try {
			productDao.updateProduct(product);
		} catch (Exception e) {
			throw new ServiceException("Failed to update product", e);
		}
	}

	@Transactional
	@Override
	public List<Product> getProductByCategory(int categoryId) throws ServiceException {
		try {
			return productDao.getProductsByCategory(categoryId);
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve products by category", e);
		}
	}

	@Transactional
	@Override
	public Product getProductById(int productId) throws ServiceException {
		try {
			Product product = productDao.getProductById(productId);
			if (product == null) {
				throw new ServiceException("Product with ID " + productId + " not found");
			}
			return product;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("Error retrieving product with ID " + productId, e);
		}
	}
}