package by.edu.academy.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import by.edu.academy.dao.BasketItemDao;
import by.edu.academy.model.basket.BasketItem;
import jakarta.persistence.*;

@Repository
public class BasketItemDaoImpl implements BasketItemDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<BasketItem> getBasketItemsByBasketId(int basketId) {
		return entityManager
				.createQuery("SELECT bi FROM BasketItem bi WHERE bi.basket.id = :basketId", BasketItem.class)
				.setParameter("basketId", basketId).getResultList();
	}

	@Override
	public void saveBasketItem(BasketItem basketItem) {
		entityManager.persist(basketItem);
	}

	@Override
	public void updateBasketItem(BasketItem basketItem) {
		entityManager.merge(basketItem);
	}

	@Override
	public void deleteBasketItem(int basketItemId) {
		BasketItem basketItem = entityManager.find(BasketItem.class, basketItemId);
		if (basketItem != null) {
			entityManager.remove(basketItem);
		}
	}

	@Override
	public BasketItem getBasketItemById(int basketItemId) {
		return entityManager.find(BasketItem.class, basketItemId);
	}

	@Override
	public BasketItem getBasketItemByBasketAndProduct(int basketId, int productId) {
		try {
			String query = "SELECT bi FROM BasketItem bi WHERE bi.basket.id = :basketId AND bi.product.id = :productId";
			return entityManager.createQuery(query, BasketItem.class).setParameter("basketId", basketId)
					.setParameter("productId", productId).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}