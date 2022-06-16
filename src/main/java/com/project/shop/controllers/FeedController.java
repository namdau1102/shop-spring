package com.project.shop.controllers;

import com.project.shop.exceptions.InvalidDataException;
import com.project.shop.exceptions.ItemNotFoundException;
import com.project.shop.models.*;
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
@RequestMapping("/feed")
@SessionAttributes("searchForm")
public class FeedController {
    private final FeedService feedService;

    @Autowired
    public FeedController(FeedService feedService) {
        this.feedService = feedService;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String getPetList(@ModelAttribute("searchForm") SearchForm searchForm,
                             Model model,
                             Pageable pageable) {
        model.addAttribute("feed_list", feedService.getAllFeeds(pageable, searchForm));
        model.addAttribute("cart_item", new CartItem());
        return "feedList";
    }

    @ModelAttribute("searchForm")
    public SearchForm loadForm() {
        return new SearchForm();
    }


    @PostMapping("/add")
    public String saveFeed(@Valid @ModelAttribute("feed") Feed feed,
                           BindingResult bindingResult,
                           MultipartFile[] multipartFile,
                           HttpServletRequest httpRequest) throws IOException {
        if (bindingResult.hasErrors())
            throw new InvalidDataException();

        feedService.save(feed, multipartFile, httpRequest);
        return "redirect:/";
    }


    @GetMapping("/cat/{id}")
    public String showAnimalsByType(@PathVariable("id") Type type,
                                    @ModelAttribute("searchForm") SearchForm searchForm,
                                    Model model,
                                    Pageable pageable) {
        model.addAttribute("feed_list", feedService.getFeedByType(type, pageable));
        model.addAttribute("cart_item", new CartItem());
        return "feedList";
    }


    @GetMapping("/{id}")
    public String showFeed(@PathVariable long id,
                           Model model) {
        if (!feedService.getFeed(id).isPresent())
            throw new ItemNotFoundException();

        model.addAttribute("cart_item", new CartItem());
        model.addAttribute("animal", feedService.getFeed(id).get());
        return "itemPage";
    }


    @PostMapping("/buy")
    public String addToCart(@ModelAttribute CartItem cartItem,
                            @RequestParam(value = "id") Feed item,
                            Model model) {
        if (item == null)
            throw new RuntimeException("Brak produktu o takim id");
        if (cartItem.getAmount() > item.getAmount()) {
            model.addAttribute("info", "Brak dostępności przedmiotu " + item.getName());
            return "forward:/feed/list";
        }
        cartItem.setItem(item);
        feedService.addToCart(cartItem, item);
        return "redirect:/user/cart";
    }


    @GetMapping("/edit/{id}")
    public String editAnimal(@PathVariable("id") Feed feed,
                             Model model) {
        model.addAttribute("animal", feed);

        return "editFeedPage";
    }


    @PostMapping("/update")
    public String updateFeed(@Valid @ModelAttribute("animal") Feed feed,
                             BindingResult bindingResult,
                             MultipartFile[] multipartFile,
                             HttpServletRequest httpServletRequest) throws IOException {
        if (bindingResult.hasErrors())
            return "editFeedPage";

        feedService.update(feed, multipartFile, httpServletRequest);
        return "redirect:/";
    }


    @Secured("ROLE_ADMIN")
    @GetMapping("/delete/{id}")
    public String deleteFeed(@PathVariable("id") Feed feed) {
        feedService.delete(feed);
        return "redirect:/feed/list";
    }
}
