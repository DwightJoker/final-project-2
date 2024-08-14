package by.edu.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.category.Category;
import by.edu.academy.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/all")
	public String getAllCategories(Model model) {
		try {
			List<Category> categories = categoryService.getAllCategories();
			model.addAttribute("categories", categories);
			return "categoryList";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	@GetMapping("/{id}")
	public String getCategoryById(@PathVariable int id, Model model) {
		try {
			Category category = categoryService.getCategoryById(id);
			model.addAttribute("category", category);
			return "index";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
}