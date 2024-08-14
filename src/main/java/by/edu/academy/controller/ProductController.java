package by.edu.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.category.Category;
import by.edu.academy.model.product.Product;
import by.edu.academy.model.user.User;
import by.edu.academy.service.ProductService;
import jakarta.servlet.http.HttpSession;
import by.edu.academy.service.CategoryService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/add")
	public String showAddProductForm(Model model) {
		model.addAttribute("product", new Product());
		try {
			model.addAttribute("categories", categoryService.getAllCategories());
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", "Failed to load categories: " + e.getMessage());
			return "error";
		}
		return "addProduct";
	}

	@PostMapping("/add")
	public String addProduct(@ModelAttribute("product") Product product,
			@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
		try {
			if (categoryId != null) {
				Category category = categoryService.getCategoryById(categoryId);
				product.setCategory(category);
			}
			productService.addProduct(product);
			return "redirect:/products/all";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "addProduct";
		}
	}

	@GetMapping("/all")
	public String getAllProducts(Model model, HttpSession session) {
		try {
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);
			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);
			List<Product> products = productService.getAllProducts();
			model.addAttribute("products", products);
			return "productList";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	@PostMapping("/delete/{id}")
	public String deleteProduct(@PathVariable int id, Model model) {
		try {
			productService.deleteProduct(id);
			return "redirect:/products/all";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	@GetMapping("/edit/{id}")
	public String showEditProductForm(@PathVariable int id, Model model) {
		try {
			Product product = productService.getProductById(id);
			model.addAttribute("product", product);
			model.addAttribute("categories", categoryService.getAllCategories());
			return "editProduct";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	@PostMapping("/edit")
	public String editProduct(@ModelAttribute("product") Product product,
			@RequestParam(value = "categoryId", required = false) Integer categoryId, Model model) {
		try {
			if (categoryId != null) {
				Category category = categoryService.getCategoryById(categoryId);
				product.setCategory(category);
			}
			productService.editProduct(product);
			return "redirect:/products/all";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "editProduct";
		}
	}

	@GetMapping("/category/{categoryId}")
	public String getProductByCategory(@PathVariable("categoryId") int categoryId, HttpSession session, Model model) {
		try {
			User user = (User) session.getAttribute("user");
			model.addAttribute("user", user);

			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);

			List<Product> products = productService.getProductByCategory(categoryId);
			model.addAttribute("products", products);

			return "productList";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
}