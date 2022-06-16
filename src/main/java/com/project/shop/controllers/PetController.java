package com.project.shop.controllers;

import com.project.shop.exceptions.InvalidDataException;
import com.project.shop.exceptions.ItemNotFoundException;
import com.project.shop.models.*;
import com.project.shop.repositories.FeedRepository;
import com.project.shop.repositories.PetRepository;
import com.project.shop.repositories.TypeRepository;
import com.project.shop.services.declarations.FileService;
import com.project.shop.services.declarations.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/pet")
@SessionAttributes("searchForm")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }


    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String getPetList(@ModelAttribute("searchForm") SearchForm searchForm,
                             Model model,
                             Pageable pageable) {
        model.addAttribute("pet_list", petService.getAllPets(pageable, searchForm));
        model.addAttribute("cart_item", new CartItem());
        System.out.println("Ten tim kiem : "+petService.getAllPets(pageable, searchForm));

        return "petList";
    }

    @ModelAttribute("searchForm")
    public SearchForm loadForm() {
        return new SearchForm();
    }

    @GetMapping("/clear")
    public String clearSearchForm(@ModelAttribute("searchForm") SearchForm searchForm) {
        searchForm.clear();
        return "redirect:/";
    }


    @PostMapping("/add")
    public String saveAnimal(@Valid @ModelAttribute("animal") Pet animal,
                             BindingResult bindingResult,
                             MultipartFile[] multipartFile,
                             HttpServletRequest httpRequest) throws IOException {
        if (bindingResult.hasErrors())
            throw new InvalidDataException();

        petService.save(animal, multipartFile, httpRequest);
        return "redirect:/";
    }


    @RequestMapping(value = "/buy", method = {RequestMethod.POST, RequestMethod.GET})
    public String addToCart(@ModelAttribute CartItem cartItem,
                            @RequestParam(value = "id") Pet item) {

        if (item == null)
            throw new ItemNotFoundException();

        cartItem.setItem(item);
        petService.addToCart(cartItem);
        return "redirect:/user/cart";
    }


    @GetMapping("/{id}")
    public String showAnimal(@PathVariable long id,
                             Model model) {
        if (!petService.getAnimal(id).isPresent())
            throw new ItemNotFoundException();

        model.addAttribute("cart_item", new CartItem());
        model.addAttribute("animal", petService.getAnimal(id).get());
        return "itemPage";
    }


    @GetMapping("/cat/{id}")
    public String showAnimalsByType(@PathVariable("id") Type type,
                                    @ModelAttribute("searchForm") SearchForm searchForm,
                                    Model model,
                                    Pageable pageable) {
        model.addAttribute("pet_list", petService.getAnimalByType(type, pageable));
        model.addAttribute("cart_item", new CartItem());
        return "petList";
    }

    @PostMapping("/cat/{id}")
    public String searchAnimals(@PathVariable("id") Type type,
                                    @ModelAttribute("searchForm") SearchForm searchForm,
                                    Model model,
                                    Pageable pageable) {
        model.addAttribute("pet_list", petService.getAllPets(pageable, searchForm));
        model.addAttribute("cart_item", new CartItem());
        return "petList";
    }

    @GetMapping("/edit/{id}")
    public String editAnimal(@PathVariable("id") Pet animal,
                             Model model) {
        model.addAttribute("animal", animal);

        return "editPage";
    }


    @PostMapping("/update")
    public String updateAnimal(@Valid @ModelAttribute("animal") Pet animal,
                               BindingResult bindingResult,
                               MultipartFile[] multipartFile,
                               HttpServletRequest httpServletRequest) throws IOException {
        if (bindingResult.hasErrors())
            return "editPage";

        petService.update(animal, multipartFile, httpServletRequest);
        return "redirect:/";
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String deleteFeed(@PathVariable("id") Pet pet) {
        petService.delete(pet);
        return "redirect:/pet/list";
    }
}
