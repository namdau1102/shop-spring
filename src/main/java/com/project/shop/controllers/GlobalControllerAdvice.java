package com.project.shop.controllers;

import com.project.shop.exceptions.InvalidDataException;
import com.project.shop.exceptions.InvalidUserDataException;
import com.project.shop.exceptions.ItemNotFoundException;
import com.project.shop.repositories.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalControllerAdvice {
    private final TypeRepository typeRepository;

    @Autowired
    public GlobalControllerAdvice(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @ModelAttribute
    public void mod(Model model) {
        model.addAttribute("item_types", typeRepository.findAll());
    }


    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(InvalidUserDataException.class)
    public String handleInvalidUserData(Model model, Exception e,
                                        HttpServletRequest req) {
        model.addAttribute("exception", e);
        model.addAttribute("url", req.getRequestURL());

        return "errors/invalidUserDataPage";
    }


    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(InvalidDataException.class)
    public String handleInvalidData(Model model, Exception e,
                                    HttpServletRequest req) {
        model.addAttribute("exception", e);
        model.addAttribute("url", req.getRequestURL());

        return "errors/invalidDataPage";
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ItemNotFoundException.class)
    public String handleItemNotFound(Model model, Exception e,
                                     HttpServletRequest req) {
        model.addAttribute("exception", e);
        model.addAttribute("url", req.getRequestURL());

        return "errors/itemNotFoundPage";
    }

}
