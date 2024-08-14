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

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.AuthData;
import by.edu.academy.model.basket.Basket;
import by.edu.academy.model.user.User;
import by.edu.academy.service.BasketService;
import by.edu.academy.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private BasketService basketService;

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user, HttpSession session, Model model) {
		try {
			userService.registration(user);
			Basket basket = basketService.createBasketForUser(user);
			session.setAttribute("basketId", basket.getId());

			return "redirect:/users/login";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", "Error creating basket: " + e.getMessage());
			return "register";
		}
	}

	@GetMapping("/login")
	public String showLoginForm(Model model) {
		model.addAttribute("authData", new AuthData());
		return "login";
	}

	@PostMapping("/login")
	public String loginUser(@ModelAttribute("authData") AuthData authData, HttpSession session, Model model) {
		try {
			User user = userService.auth(authData);
			session.setAttribute("user", user);
			session.setAttribute("userId", user.getId());
			session.setAttribute("userRole", user.getRoles());
			int basketId = basketService.getBasketByUserId(user.getId()).getId();
			session.setAttribute("basketId", basketId);
			return "redirect:/";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "login";
		}
	}

	@GetMapping("/all")
	public String getAllUsers(Model model) {
		try {
			List<User> users = userService.getAllUsers();
			model.addAttribute("users", users);
			return "userList";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		return "redirect:/";
	}

	@PostMapping("/delete/{id}")
	public String deleteUser(@PathVariable int id, Model model) {
		try {
			userService.deleteUser(id);
			return "redirect:/users/all";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
}