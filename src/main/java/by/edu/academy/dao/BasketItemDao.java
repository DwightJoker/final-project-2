package by.edu.academy.dao;

import java.util.List;

import by.edu.academy.exception.DaoException;
import by.edu.academy.model.basket.BasketItem;

public interface BasketItemDao {

	List<BasketItem> getBasketItemsByBasketId(int basketId) throws DaoException;

	void saveBasketItem(BasketItem basketItem) throws DaoException;

	void updateBasketItem(BasketItem basketItem) throws DaoException;

	void deleteBasketItem(int basketItemId) throws DaoException;

	BasketItem getBasketItemById(int basketItemId) throws DaoException;

	BasketItem getBasketItemByBasketAndProduct(int basketId, int productId) throws DaoException;

}
