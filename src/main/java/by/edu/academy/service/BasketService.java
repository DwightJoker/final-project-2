package by.edu.academy.service;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.basket.Basket;
import by.edu.academy.model.user.User;

public interface BasketService {

	Basket getBasketByUserId(int userId) throws ServiceException;

	void saveBasket(Basket basket) throws ServiceException;

	void updateBasket(Basket basket) throws ServiceException;

	void deleteBasket(int basketId) throws ServiceException;

	Basket createBasketForUser(User user) throws ServiceException;

	public Long calculateTotalPrice(int basketId) throws ServiceException;

}