package com.is.controller;

import com.is.model.Book;
import com.is.model.User;
import com.is.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by blackghost on 1/18/2017.
 */
@Controller
public class HomeController {
    @Autowired
    private HomeService homeService;

    @RequestMapping(value = "/home.htm", method = RequestMethod.GET)
    public ModelAndView goToHome() {
        ModelAndView modelView = new ModelAndView("home");
        return modelView;
    }

    @RequestMapping(value = "/cart.htm", method = RequestMethod.GET)
    public ModelAndView goToCart() {
        ModelAndView modelView = new ModelAndView("cart");
        return modelView;
    }

    @RequestMapping(value = "/books.htm", method = RequestMethod.GET)
    public ModelAndView goToBooks() {
        ModelAndView modelView = new ModelAndView("books");
        return modelView;
    }

    @RequestMapping(value = "/addBook.htm", method = RequestMethod.POST, params = {"addBook=addBook"})
    public ModelAndView addToCart(@RequestParam("userId") int userId, @RequestParam("name") String name ){
        ModelAndView modelAndView = new ModelAndView("books");
        int id = homeService.getBooks().getIdByName(name);
        homeService.addToCart(id, userId);
        System.out.println("ADAUGAT");
        return  modelAndView;
    }
}
