package com.project.shop.controllers;

import com.project.shop.models.Order;
import com.project.shop.models.OrderType;
import com.project.shop.models.Role;
import com.project.shop.models.User;
import com.project.shop.repositories.OrderTypeRepository;
import com.project.shop.repositories.RoleRepository;
import com.project.shop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;

@Controller
@SessionAttributes("shoppingCart")
public class MainController {
    @GetMapping("/main")
    public String mainRedirect(Model model) {
        return "mainPage";
    }
}
