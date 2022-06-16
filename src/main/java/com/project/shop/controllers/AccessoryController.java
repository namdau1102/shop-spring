package com.project.shop.controllers;

import com.project.shop.exceptions.InvalidDataException;
import com.project.shop.exceptions.ItemNotFoundException;
import com.project.shop.models.*;
import com.project.shop.services.declarations.AccessoryService;
import com.project.shop.services.declarations.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/accessory")
@SessionAttributes("searchForm")
public class AccessoryController {
    private final AccessoryService accessoryService;

    @Autowired
    public AccessoryController(AccessoryService accessoryService) {
        this.accessoryService = accessoryService;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String getPetList(@ModelAttribute("searchForm") SearchForm searchForm,
                             Model model,
                             Pageable pageable) {
        model.addAttribute("accessory_list", accessoryService.getAllAccessories(pageable, searchForm));
        model.addAttribute("cart_item", new CartItem());
        return "accessoryList";
    }

    @ModelAttribute("searchForm")
    public SearchForm loadForm() {
        return new SearchForm();
    }


    @PostMapping("/add")
    public String saveAccessory(@Valid @ModelAttribute("accessory") Accessory accessory,
                                BindingResult bindingResult,
                                MultipartFile[] multipartFile,
                                HttpServletRequest httpRequest) throws IOException {
        if (bindingResult.hasErrors())
            throw new InvalidDataException();

        accessoryService.save(accessory, multipartFile, httpRequest);
        return "redirect:/";
    }


    @GetMapping("/{id}")
    public String showAccessory(@PathVariable long id,
                                Model model) {
        if (!accessoryService.getAccessory(id).isPresent())
            throw new ItemNotFoundException();

        model.addAttribute("cart_item", new CartItem());
        model.addAttribute("animal", accessoryService.getAccessory(id).get());
        return "itemPage";
    }


    @PostMapping("/buy")
    public String addToCart(@ModelAttribute CartItem cartItem,
                            @RequestParam(value = "id") Accessory item,
                            Model model) {
        if (item == null)
            throw new RuntimeException("Brak produktu o takim id");
        if (cartItem.getAmount() > item.getAmount()) {
            model.addAttribute("info", "Brak dostępności przedmiotu " + item.getName());
            return "forward:/accessory/list";
        }

        cartItem.setItem(item);
        accessoryService.addToCart(cartItem, item);
        return "redirect:/user/cart";
    }


    @GetMapping("/acc/{id}")
    public String showAnimalsByType(@PathVariable("id") Type type,
                                    @ModelAttribute("searchForm") SearchForm searchForm,
                                    Model model,
                                    Pageable pageable) {
        model.addAttribute("accessory_list", accessoryService.getAccessoryByType(type, pageable));
        model.addAttribute("cart_item", new CartItem());
        return "accessoryList";
    }


    @GetMapping("/edit/{id}")
    public String editAccessory(@PathVariable("id") Accessory accessory,
                                Model model) {
        model.addAttribute("animal", accessory);

        return "editAccessoryPage";
    }


    @PostMapping("/update")
    public String updateAccessory(@Valid @ModelAttribute("animal") Accessory accessory,
                                  BindingResult bindingResult,
                                  MultipartFile[] multipartFile,
                                  HttpServletRequest httpServletRequest) throws IOException {
        if (bindingResult.hasErrors())
            return "editAccessoryPage";

        accessoryService.update(accessory, multipartFile, httpServletRequest);
        return "redirect:/";
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String deleteFeed(@PathVariable("id") Accessory accessory) {
        accessoryService.delete(accessory);
        return "redirect:/accessory/list";
    }
}
