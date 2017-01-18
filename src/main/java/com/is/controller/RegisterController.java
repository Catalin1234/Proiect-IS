package com.is.controller;

import com.is.dao.implementation.DefaultUserDao;
import com.is.model.User;
import com.is.services.LoginService;
import com.is.services.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * Created by catal on 1/18/2017.
 */
@Controller
public class RegisterController {

    @Autowired
    private RegisterService registerService;


    @RequestMapping(value = "/register.htm", method = RequestMethod.GET)
    public String enter() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("username") String username,
                                 @RequestParam("password") String password,
                                 @RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 HttpSession session) {
        ModelAndView modelView;
        if (registerService.validUsername(username)) {
            registerService.addUser(password,username,firstName,lastName,0);
            modelView = new ModelAndView("login");
            return modelView;
        }
        else{
            modelView = new ModelAndView("register");
            modelView.addObject("message","Username already exists!");
            return modelView;
       }
    }


}
