package by.edu.academy.dao;

import by.edu.academy.model.basket.Basket;

public interface BasketDao {

	Basket getBasketByUserId(int userId);

	void saveBasket(Basket basket);

	void updateBasket(Basket basket);

	void deleteBasket(int basketId);

	Basket createBasket(Basket basket);

	Long calculateTotalPrice(int basketId);
}
