package by.edu.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.edu.academy.dao.BasketDao;
import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.basket.Basket;
import by.edu.academy.model.user.User;
import by.edu.academy.service.BasketService;

@Service
public class BasketServiceImpl implements BasketService {

	@Autowired
	private BasketDao basketDao;

	@Transactional(readOnly = true)
	@Override
	public Basket getBasketByUserId(int userId) throws ServiceException {
		try {
			return basketDao.getBasketByUserId(userId);
		} catch (Exception e) {
			throw new ServiceException("Failed to retrieve basket for user with ID " + userId, e);
		}
	}

	@Transactional
	@Override
	public void saveBasket(Basket basket) throws ServiceException {
		try {
			basketDao.saveBasket(basket);
		} catch (Exception e) {
			throw new ServiceException("Failed to save basket", e);
		}
	}

	@Transactional
	@Override
	public void updateBasket(Basket basket) throws ServiceException {
		try {
			basketDao.updateBasket(basket);
		} catch (Exception e) {
			throw new ServiceException("Failed to update basket", e);
		}
	}

	@Transactional
	@Override
	public void deleteBasket(int basketId) throws ServiceException {
		try {
			basketDao.deleteBasket(basketId);
		} catch (Exception e) {
			throw new ServiceException("Failed to delete basket with ID " + basketId, e);
		}
	}

	@Transactional
	@Override
	public Basket createBasketForUser(User user) {
		Basket basket = new Basket();
		basket.setUser(user);
		basket.setTotalPrice(0.0);
		basketDao.saveBasket(basket);

		return basket;
	}

	@Transactional
	@Override
	public Long calculateTotalPrice(int basketId) throws ServiceException {
		try {
			return basketDao.calculateTotalPrice(basketId);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException("Failed to calculate total price for basket with ID " + basketId, e);
		}
	}
}