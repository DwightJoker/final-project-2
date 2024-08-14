package by.edu.academy.dao.impl;

import org.springframework.stereotype.Repository;

import by.edu.academy.dao.BasketDao;
import by.edu.academy.model.basket.Basket;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

@Repository
public class BasketDaoImpl implements BasketDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Basket getBasketByUserId(int userId) {
		try {
			return entityManager.createQuery("SELECT b FROM Basket b WHERE b.user.id = :userId", Basket.class)
					.setParameter("userId", userId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public void saveBasket(Basket basket) {
		entityManager.persist(basket);

	}

	@Override
	public void updateBasket(Basket basket) {
		entityManager.merge(basket);

	}

	@Override
	public void deleteBasket(int basketId) {
		Basket basket = entityManager.find(Basket.class, basketId);
		if (basket != null) {
			entityManager.remove(basket);
		}

	}

	@Override
	public Basket createBasket(Basket basket) {
		if (basket.getId() == 0) {
			entityManager.persist(basket);
			return basket;
		} else {
			return entityManager.merge(basket);
		}
	}

	@Override
	public Long calculateTotalPrice(int basketId) {
		try {
			System.out.println("Calculating total price for basket ID: " + basketId);
			String query = "SELECT SUM(bi.product.price * bi.quantity) FROM BasketItem bi WHERE bi.basket.id = :basketId";
			Long totalPrice = (Long) entityManager.createQuery(query).setParameter("basketId", basketId)
					.getSingleResult();
			System.out.println("Calculated total price: " + totalPrice);
			return (long) (totalPrice != null ? totalPrice : 0.0);
		} catch (Exception e) {
			System.err.println("Error calculating total price: " + e.getMessage());
			throw new RuntimeException("Failed to calculate total price for basket with ID " + basketId, e);
		}
	}
}
