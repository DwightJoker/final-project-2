package by.edu.academy.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.basket.Basket;
import by.edu.academy.model.basket.BasketItem;
import by.edu.academy.model.product.Product;
import by.edu.academy.service.BasketItemService;
import by.edu.academy.service.BasketService;
import by.edu.academy.service.ProductService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/baskets")
public class BasketController {

	@Autowired
	private BasketService basketService;

	@Autowired
	private BasketItemService basketItemService;

	@Autowired
	private ProductService productService;

	@GetMapping("/user/{userId}")
	public String getBasketByUserId(@PathVariable int userId, Model model) {
		try {
			Basket basket = basketService.getBasketByUserId(userId);
			model.addAttribute("basket", basket);
			model.addAttribute("basketItems", basketItemService.getBasketItemsByBasketId(basket.getId()));
			double totalPrice = basketService.calculateTotalPrice(basket.getId());
			model.addAttribute("totalPrice", totalPrice);
			return "basketDetail";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			e.printStackTrace();
			return "error";
		}
	}

	@PostMapping("/add-item")
	public String addItemToBasket(@RequestParam int basketId, @RequestParam int productId, @RequestParam int quantity,
			Model model) {
		try {
			Basket basket = basketService.getBasketByUserId(basketId);
			Product product = productService.getProductById(productId);

			BasketItem existingItem = basketItemService.getBasketItemByBasketAndProduct(basketId, productId);

			if (existingItem != null) {

				existingItem.setQuantity(existingItem.getQuantity() + quantity);
				basketItemService.updateBasketItem(existingItem);
			} else {

				basketItemService.addBasketItem(new BasketItem(basket, product, quantity));
			}

			return "redirect:/baskets/user/" + basketId;
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	@PostMapping("/remove-item/{itemId}")
	public String removeItemFromBasket(@PathVariable int itemId, HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			model.addAttribute("errorMessage", "Ошибка: Пользователь не авторизован.");
			return "error";
		}

		try {
			basketItemService.removeBasketItem(itemId);
			return "redirect:/baskets/user/" + userId;
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	@PostMapping("/update")
	public String updateBasketItemQuantity(@RequestParam Map<String, String> params, HttpSession session, Model model) {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			model.addAttribute("errorMessage", "Ошибка: Пользователь не авторизован.");
			return "error";
		}

		try {
			Basket basket = basketService.getBasketByUserId(userId);
			List<BasketItem> basketItems = basketItemService.getBasketItemsByBasketId(basket.getId());

			for (String key : params.keySet()) {
				if (key.startsWith("quantities[")) {
					String idStr = key.substring("quantities[".length(), key.length() - 1);
					int basketItemId = Integer.parseInt(idStr);
					int quantity = Integer.parseInt(params.get(key));

					BasketItem basketItem = basketItems.stream().filter(item -> item.getIdbasket_item() == basketItemId)
							.findFirst()
							.orElseThrow(() -> new ServiceException("BasketItem not found with ID: " + basketItemId));

					basketItem.setQuantity(quantity);
					basketItemService.updateBasketItem(basketItem);
				}
			}

			long totalPrice = basketService.calculateTotalPrice(basket.getId());

			model.addAttribute("basket", basket);
			model.addAttribute("basketItems", basketItemService.getBasketItemsByBasketId(basket.getId()));
			model.addAttribute("totalPrice", totalPrice);

			return "basketDetail";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

}