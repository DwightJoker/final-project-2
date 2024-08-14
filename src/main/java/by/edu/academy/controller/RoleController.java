package by.edu.academy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import by.edu.academy.exception.ServiceException;
import by.edu.academy.model.role.Role;
import by.edu.academy.service.RoleService;

@Controller
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@GetMapping("/all")
	public String getAllRoles(Model model) {
		try {
			List<Role> roles = roleService.getAllRoles();
			model.addAttribute("roles", roles);
			return "roleList";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}

	@GetMapping("/{id}")
	public String getRoleById(@PathVariable int id, Model model) {
		try {
			Role role = roleService.getRoleById(id);
			model.addAttribute("role", role);
			return "roleDetail";
		} catch (ServiceException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "error";
		}
	}
}
