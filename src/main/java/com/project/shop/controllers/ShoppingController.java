package com.project.shop.controllers;

import com.project.shop.exceptions.InvalidUserDataException;
import com.project.shop.exceptions.ItemNotFoundException;
import com.project.shop.models.Cart;
import com.project.shop.models.Item;
import com.project.shop.services.declarations.ShoppingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("shoppingCart")
public class ShoppingController {
    private final ShoppingService shoppingService;

    @Autowired
    public ShoppingController(ShoppingService shoppingService) {
        this.shoppingService = shoppingService;
    }


    @GetMapping("/order")
    public String createOrder(Model model) {
        if (!shoppingService.isUserDetailsCorrect())
            throw new InvalidUserDataException();

        String error = shoppingService.existsInDatabase();
        if (!StringUtils.isBlank(error)) {
            model.addAttribute("error", error);
            return "forward:/user/cart";
        }
        shoppingService.createOrder();
        return "redirect:/";
    }


    @GetMapping("/status/{id}")
    public String changeOrderStatus(@PathVariable("id") int id) {
        shoppingService.changeOrderStatus(id);
        return "redirect:/user/summary";
    }


    @GetMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable("id") int id,
                              @RequestParam("us") int user) {
        shoppingService.cancelOrder(id);
        if (user == 1)
            return "redirect:/user/summary";
        return "redirect:/user/orders";
    }


    @GetMapping("/delete/{id}")
    public String deleteFromCart(@PathVariable("id") Integer item) {
        if (item == null)
            throw new ItemNotFoundException();

        shoppingService.deleteFromCart(item);
        return "redirect:/user/cart";
    }
}
