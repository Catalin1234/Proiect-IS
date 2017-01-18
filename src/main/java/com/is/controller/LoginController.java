package com.is.controller;

import com.is.model.Training;
import com.is.model.User;
import com.is.services.EditorService;
import com.is.services.EmployeeService;
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
    @Autowired
    private EditorService editorService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/login.htm", method = RequestMethod.GET)
    public String enter() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        User user;
        ModelAndView modelView;
        if (loginService.loginUser(username, password)) {
            user = loginService.getUser();
            session.setAttribute("name", user.getFirstName());
            session.setAttribute("role", user.getRole());
            if (user.getRole() == 1) {
                modelView = new ModelAndView("employee");
                List<Training> listOfAvailableTrainingsForEmployee = employeeService.viewTraining(user);
                session.setAttribute("list", listOfAvailableTrainingsForEmployee);
                System.out.println(listOfAvailableTrainingsForEmployee);
                session.setAttribute("user", user);
                return modelView;
            } else {
                modelView = new ModelAndView("editor");
                List<Training> trainingList = editorService.getDefaultTrainingDao().getAllTrainings();
                modelView.addObject("trainingList", trainingList);
                modelView.addObject("name","Hello, " + user.getFirstName());
                return modelView;
            }
        } else {
            modelView = new ModelAndView("login");
            modelView.addObject("message","Invalid credentials");
            return modelView;
        }
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
