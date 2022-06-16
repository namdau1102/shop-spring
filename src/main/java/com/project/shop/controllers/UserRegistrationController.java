package com.project.shop.controllers;

import com.project.shop.models.User;
import com.project.shop.services.declarations.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@SessionAttributes("shoppingCart")
public class UserRegistrationController {
    private final UserService userService;

    @Autowired
    public UserRegistrationController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/registration")
    private String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        
        return "registrationForm";
    }


    @PostMapping("/registration")
    private String registerNewUser(@Valid @ModelAttribute("user") User userForm,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors())
            return "registrationForm";

        userService.save(userForm);
        return "redirect:/acc_created";
    }


    @GetMapping("/acc_created")
    public String successRegistration() {
        return "accountCreatedPage";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("enabled", "id", "expired", "locked");
    }
}
