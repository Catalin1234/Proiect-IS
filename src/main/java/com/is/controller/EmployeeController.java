package com.is.controller;

import com.is.model.Training;
import com.is.model.User;
import com.is.services.EditorService;
import com.is.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


/**
 * Created by ctimbus on 8/8/2016.
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EditorService editorService;

    private boolean tooManyPeople;

    public EmployeeController() {
        System.out.println("entered");
    }

    @RequestMapping(value = "/getTraining.htm", method = RequestMethod.GET)
    public ModelAndView getTraining(@RequestParam(value = "trainingName", required = false) String trainingName, HttpSession httpSession) {
        ModelAndView modelView = new ModelAndView("employeeForm");
        Training trainingToAdd = editorService.getTrainingByName(trainingName);
        modelView.addObject("training", trainingToAdd);
        return modelView;
    }

    @RequestMapping(value = "/registerEmployee.htm", method = RequestMethod.POST)
    public ModelAndView registerEmployee(@RequestParam(value = "trainingName", required = false) String trainingName,
                                         @RequestParam(value = "trainerName", required = false) String trainerName,
                                         @RequestParam(value = "startDate", required = false) String startDate,
                                         @RequestParam(value = "stopDate", required = false) String stopDate,
                                         @RequestParam(value = "grade", required = false) String grade,
                                         @RequestParam(value = "technology", required = false) String technology,
                                         @RequestParam(value = "numberPeople", required = false) String numberPeople,
                                         HttpSession httpSession) {
        User user = (User) httpSession.getAttribute("user");
        if (employeeService.registerEmployee(user, trainingName)) {
            System.out.println(user.getLastName());
            System.out.println(user.getFirstName());
            return new ModelAndView("registerSuccess");

        } else {
            tooManyPeople = employeeService.areTooManyPeople();
            if (tooManyPeople) {
                return new ModelAndView("tooManyPeople");
            }
            System.out.println("Try again :D");
            return new ModelAndView("registerUnsuccessful");
        }
    }



    @RequestMapping(value ="/employee.htm",method = RequestMethod.POST)
    public ModelAndView registerEmployeeVote(@RequestParam(value = "trainingName")String trainingName,
                                     @RequestParam(value = "rating",required = false)int rating,
                                     HttpSession httpSession) {
        //System.out.println("Training "+trainingName+" Rating "+rating);
        User user = (User) httpSession.getAttribute("user");
        System.out.println("AICI " +user);
        ModelAndView modelView = new ModelAndView("employee");
        if(employeeService.canEmployeeVote(user,trainingName)) {
            System.out.println("merem sa votam");
            employeeService.registerEmployeeRating(user.getUserId(),trainingName,rating);

        }
        return modelView;
    }
}
