package by.edu.academy.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import by.edu.academy.dao.ProductDao;
import by.edu.academy.model.basket.BasketItem;
import by.edu.academy.model.product.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addProduct(Product product) {
		getCurrentSession().persist(product);
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> products = getCurrentSession().createQuery("SELECT p FROM Product p", Product.class)
				.getResultList();
		System.out.println("Retrieved products123: " + products);
		return products;
	}

	@Override
	public void deleteProduct(int productId) {
		Session session = getCurrentSession();
		Product product = session.get(Product.class, productId);
		if (product != null) {
			for (BasketItem item : product.getBasketItems()) {
				session.remove(item);
			}
			session.remove(product);
		}
	}

	@Override
	public void updateProduct(Product product) {
		getCurrentSession().merge(product);
	}

	@Override
	public List<Product> getProductsByCategory(int categoryId) {
		String hql = "from Product where category.id = :categoryId";
		return getCurrentSession().createQuery(hql, Product.class).setParameter("categoryId", categoryId)
				.getResultList();
	}

	@Override
	public Product getProductById(int productId) {
		return getCurrentSession().get(Product.class, productId);
	}

}
