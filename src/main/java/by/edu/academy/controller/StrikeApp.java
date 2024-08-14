package by.edu.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.basket.Basket;
import by.edu.academy.model.basket.BasketItem;
import by.edu.academy.model.category.Category;
import by.edu.academy.model.product.Product;
import by.edu.academy.service.BasketItemService;
import by.edu.academy.service.BasketService;
import by.edu.academy.service.CategoryService;
import by.edu.academy.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StrikeApp {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ProductService productService;

	@Autowired
	private BasketItemService basketItemService;

	@Autowired
	private BasketService basketService;

	@GetMapping("/")
	public String index(Model model, HttpSession session) throws ServiceException {
		List<Category> categories = categoryService.getAllCategories();
		List<Product> products = productService.getAllProducts();

		model.addAttribute("categories", categories);
		model.addAttribute("products", products);

		return "index";

	}

	@PostMapping("/basket/add")
	public String addToBasket(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity,
			HttpServletRequest request, HttpSession session) throws ServiceException {
		Integer userId = (Integer) session.getAttribute("userId");

		if (userId == null) {
			return "error";
		}

		Basket basket = basketService.getBasketByUserId(userId);
		Product product = productService.getProductById(productId);
		basketItemService.addBasketItem(new BasketItem(basket, product, quantity));

		String referer = request.getHeader("Referer");
		return "redirect:" + (referer != null ? referer : "/");
	}

	@GetMapping("/vacancies")
	public String vacancies() {
		return "fff";
	}

	@GetMapping("/about")
	public String about() {
		return "fff";
	}

	@GetMapping("/contact")
	public String contact() {
		return "fff";
	}

	@GetMapping("/advertise")
	public String advertise() {
		return "fff";
	}
}
