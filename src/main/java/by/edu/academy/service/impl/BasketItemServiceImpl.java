package by.edu.academy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.edu.academy.dao.BasketItemDao;
import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.basket.BasketItem;
import by.edu.academy.service.BasketItemService;

@Service
public class BasketItemServiceImpl implements BasketItemService {

	@Autowired
	private BasketItemDao basketItemDao;

	@Transactional(readOnly = true)
	@Override
	public List<BasketItem> getBasketItemsByBasketId(int basketId) throws ServiceException {
		try {
			return basketItemDao.getBasketItemsByBasketId(basketId);
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve basket items for basket with ID " + basketId, e);
		}
	}

	@Transactional
	@Override
	public void addBasketItem(BasketItem basketItem) throws ServiceException {
		try {
			basketItemDao.saveBasketItem(basketItem);
		} catch (Exception e) {
			throw new ServiceException("Failed to add basket item", e);
		}
	}

	@Transactional
	@Override
	public void updateBasketItem(BasketItem basketItem) throws ServiceException {
		try {
			basketItemDao.updateBasketItem(basketItem);
		} catch (Exception e) {
			throw new ServiceException("Failed to update basket item", e);
		}
	}

	@Transactional
	@Override
	public void removeBasketItem(int basketItemId) throws ServiceException {
		try {
			BasketItem basketItem = basketItemDao.getBasketItemById(basketItemId);
			if (basketItem != null) {
				basketItemDao.deleteBasketItem(basketItemId);
			} else {
				throw new ServiceException("BasketItem not found with ID " + basketItemId);
			}
		} catch (Exception e) {
			throw new ServiceException("Failed to remove basket item with ID " + basketItemId, e);
		}
	}

	@Transactional
	@Override
	public BasketItem getBasketItemById(int basketItemId) throws ServiceException {
		try {
			return basketItemDao.getBasketItemById(basketItemId);
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve basket item with ID " + basketItemId, e);
		}
	}

	@Transactional
	@Override
	public BasketItem getBasketItemByBasketAndProduct(int basketId, int productId) throws ServiceException {
		try {
			return basketItemDao.getBasketItemByBasketAndProduct(basketId, productId);
		} catch (Exception e) {
			throw new ServiceException(
					"Failed to retrieve basket item for basket ID " + basketId + " and product ID " + productId, e);
		}
	}
}