package com.is.controller;

import com.is.model.User;
import com.is.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by sprodan on 8/3/2016.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String enter() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        ModelAndView modelView;
        if (loginService.loginUser(username, password)) {
            System.out.println("Logged in!");
            modelView = new ModelAndView("register");
        } else {
            modelView = new ModelAndView("login");
            modelView.addObject("message","Invalid credentials");
        }
        return modelView;
    }

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET, params = {"logout=logout"})
    public ModelAndView logout(HttpSession session) {
        ModelAndView modelView;
        session.setAttribute("message", "You have been logged out.");
        modelView = new ModelAndView("login");
        session.invalidate();
        return modelView;
    }

}
