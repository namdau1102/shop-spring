package com.project.shop.controllers;

import com.project.shop.models.*;
import com.project.shop.repositories.UserRepository;
import com.project.shop.services.declarations.AddressService;
import com.project.shop.services.declarations.MailService;
import com.project.shop.services.declarations.ShoppingService;
import com.project.shop.services.declarations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/user")
public class UserController {
	private final ShoppingService shoppingService;
	
	@Autowired
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	private final MailService mailService;
	private final AddressService addressService;

	@Autowired
	public UserController(ShoppingService shoppingService, UserService userService, AddressService addressService,
			MailService mailService,PasswordEncoder passwordEncoder) {
		this.shoppingService = shoppingService;
		this.userService = userService;
		this.mailService = mailService;
		this.addressService = addressService;
		this.passwordEncoder=passwordEncoder;
	}

	@GetMapping("/restpassword")
	public String showRestPassword(Model model) {
		model.addAttribute("email", new UserEmail());
		return "restPass";
	}

	@GetMapping("/comletionPass")
	public String showComletionPass(Model model, HttpServletRequest request) {
		model.addAttribute("useremail", new UserEmail());
		String email = null;
		String code = null;
		if (request.getSession().getAttribute("email") != null && request.getSession().getAttribute("code") != null) {
			return "completionPass";
		}
		return "redirect:/";
	}

	@PostMapping("/comletionPass")
	public String ComletionPass(Model model, HttpServletRequest request,
			@Valid @ModelAttribute("useremail") UserEmail useremail) {
		User user = (User) request.getSession().getAttribute("user");
		if (!useremail.getPassword().equals(useremail.getCompletionpassword())) {
			model.addAttribute("errorPass", "Mật khẩu không trùng khớp");
			return "completionPass";
		} else {
			user.setPassword(useremail.getPassword());
			userService.save(user);
			return "redirect:/";
		}
	}

	@GetMapping("/completionCode")
	public String comletionCode(Model model, HttpServletRequest request) {
		String email = null;
		String code = null;
		model.addAttribute("email", new UserEmail());
		if (request.getSession().getAttribute("email") != null && request.getSession().getAttribute("code") != null) {
			email = request.getSession().getAttribute("email").toString();
			code = String.valueOf(request.getSession().getAttribute("code"));
			model.addAttribute("codeRestPass", code);
			model.addAttribute("emailRestPass", email);
			return "completionCode";

		}
		return "redirect:/";
	}

	@PostMapping("/completionCode")
	public String completionSuccess(Model model, @Valid @ModelAttribute("email") UserEmail email,
			HttpServletRequest request) {
		System.out.println("Code " + email.getCode());
		HttpSession session = request.getSession();

		String code = null;
		String emailPr = null;
		if (request.getSession().getAttribute("email") != null && request.getSession().getAttribute("code") != null) {
			code = String.valueOf(request.getSession().getAttribute("code"));
			if (code.equals(email.getCode())) {
				emailPr = request.getSession().getAttribute("email").toString();
				User user = userService.findUserByEmail(emailPr);
				session.setMaxInactiveInterval(60);
				request.getSession().setAttribute("user", user);
				return "redirect:/user/comletionPass";

			}
			model.addAttribute("error", "Mã xác nhận không chính xác");
			return "completionCode";
		}

		return "restPass";
	}

	@PostMapping("/restpassword")
	public String restPassword(Model model, @Valid @ModelAttribute("email") UserEmail email,
			BindingResult bindingResult, HttpServletRequest request) {
		User user = userService.findUserByEmail(email.getEmail());
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60);
		if (user != null) {
			Random rand = new Random();
			int value = rand.nextInt(99999999);
			request.getSession().setAttribute("code", value);
			request.getSession().setAttribute("email", user.getEmail());
			model.addAttribute("mess", "Email đã tồn tai");
			mailService.sendCode(value, "Thay đổi mật khẩu", user.getEmail());
			return "redirect:/user/completionCode";
		} else {
			model.addAttribute("mess", "Email Không tồn tại");
			return "restPass";

		}

	}

	@GetMapping("/cart")
	public String getShoppingCart(Model model) {
		model.addAttribute("cart_items", shoppingService.getCart());
		return "cartPage";
	}

	@GetMapping("/orders")
	public String getOrders(Model model) {
		model.addAttribute("orders", shoppingService.getUserOrders());
		return "ordersPage";
	}

	// ------ Panel admina

	@GetMapping("/summary")
	@Secured("ROLE_ADMIN")
	public String getSummary(Model model, Pageable pageable) {
		model.addAttribute("orders", shoppingService.getAllOrders());
		model.addAttribute("animal", new Pet());
		model.addAttribute("feed", new Feed());
		model.addAttribute("accessory", new Accessory());
		return "adminPage";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/list")
	public String getUserList(Model model, Pageable pageable) {
		model.addAttribute("list", userService.findAllUsers(pageable));
		return "userListPage";
	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/edit/{id}")
	public String editUserState(@PathVariable("id") User user) {
		userService.editUserLock(user);
		return "redirect:/user/list";
	}

	// ------ PANEL USERA

	@PostMapping("/updateuser")
	public String editEmailUser(@Valid @ModelAttribute("email") UserEmail userEmail, BindingResult bindingResult) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user2=null;
		if(bindingResult.hasErrors()) {
			return "redirect:/";
		}
		else {
			 user2 = userService.findUserByEmail(user.getEmail());
			 user2.setEmail(userEmail.getEmail());
			 userService.updateUserByEmail(user.getEmail(),user2.getEmail());
		}
		return "redirect:/user/details";

	}
	
	@GetMapping("/details")
	public String changeUserDetails(Model model) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (user != null) {
			User userFromDb = userService.find(user.getId());
			if (userFromDb != null) {
				model.addAttribute("user", userFromDb.getAddress());
				model.addAttribute("email", userFromDb);
				return "userPage";
			}
			return "redirect:/";

		}
		return "redirect:/";
	}

	@PostMapping("/update")
	public String updateUser(@Valid @ModelAttribute("userForm") Address address, BindingResult bindingResult) {
		if (bindingResult.hasErrors())
			return "userPage";
		addressService.update(address);
		return "redirect:/";
	}
}
