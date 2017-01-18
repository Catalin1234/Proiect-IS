package com.is.controller;

import com.is.model.Training;
import com.is.services.EditorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by cnarita on 8/8/2016.
 */
@Controller
public class EditorController {

    @Autowired
    private EditorService editorService;

    @RequestMapping(value = "/addTraining.htm", method = RequestMethod.POST)
    public ModelAndView addTraining(@RequestParam(value = "trainingId") int trainingId,
                                    @RequestParam(value = "trainingName") String trainingName,
                                    @RequestParam(value = "startDate") String startDate,
                                    @RequestParam(value = "endDate") String endDate,
                                    @RequestParam(value = "grade") String grade,
                                    @RequestParam(value = "trainerName") String trainerName,
                                    @RequestParam(value = "technology") String technology,
                                    @RequestParam(value = "places") Integer places,
                                    @RequestParam(value = "action") String action) throws ParseException {
        ModelAndView modelAndView = new ModelAndView("editor");
        Date astartDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
        Date aendDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
        Training training = new Training(trainingId, trainingName, trainerName, astartDate, aendDate, grade, technology, places);
        System.out.println(training);
        if (action.equals("add")) {
            if(editorService.checkTrainingConsistency(training)) {
                editorService.addTraining(trainingName, trainerName, astartDate, aendDate, grade, technology, places);
            }
        } else {
            if(editorService.checkTrainingConsistency(training)) {
                editorService.getDefaultTrainingDao().updateTraining(training);
            }
        }
        List<Training> trainingList = editorService.getDefaultTrainingDao().getAllTrainings();
        modelAndView.addObject("trainingList", trainingList);
        return modelAndView;
    }

    @RequestMapping(value = "/delete.htm", method = RequestMethod.POST)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView;
        String[] ids = request.getParameterValues("checked");
        editorService.deleteSelectedTrainings(ids);
        modelAndView = new ModelAndView("editor");
        List<Training> trainingList = editorService.getDefaultTrainingDao().getAllTrainings();
        modelAndView.addObject("trainingList", trainingList);
        return modelAndView;
    }
    @RequestMapping(value = "/editor.htm", method = RequestMethod.POST)
    public ModelAndView edit(@RequestParam("trainingId") int trainingId) throws ParseException{

        ModelAndView modelAndView = new ModelAndView("editor");
        Training training = editorService.getDefaultTrainingDao().searchTrainingById(trainingId);
        modelAndView.addObject("training",training);
        List<Training> trainingList = editorService.getDefaultTrainingDao().getAllTrainings();
        modelAndView.addObject("trainingList", trainingList);
        return modelAndView;
    }
}
