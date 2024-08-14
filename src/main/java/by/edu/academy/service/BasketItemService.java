package by.edu.academy.service;

import java.util.List;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.basket.BasketItem;

public interface BasketItemService {

	List<BasketItem> getBasketItemsByBasketId(int basketId) throws ServiceException;

	void addBasketItem(BasketItem basketItem) throws ServiceException;

	void updateBasketItem(BasketItem basketItem) throws ServiceException;

	void removeBasketItem(int basketItemId) throws ServiceException;

	public BasketItem getBasketItemById(int basketItemId) throws ServiceException;

	public BasketItem getBasketItemByBasketAndProduct(int basketId, int productId) throws ServiceException;
}