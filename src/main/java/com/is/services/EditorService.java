package com.is.services;

import com.is.dao.implementation.DefaultEnrollmentDao;
import com.is.dao.implementation.DefaultRatingDao;
import com.is.dao.implementation.DefaultTrainingDao;
import com.is.model.Training;
import com.is.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Created by datoderici on 8/5/2016.
 */
@Service
public class EditorService {

    @Autowired
    private DefaultTrainingDao defaultTrainingDao;
    @Autowired
    private DefaultEnrollmentDao defaultEnrollmentDao;
    @Autowired
    private DefaultRatingDao defaulRatingDao;

    public DefaultTrainingDao getDefaultTrainingDao() {
        return defaultTrainingDao;
    }

    public void setDefaultTrainingDao(DefaultTrainingDao defaultTrainingDao) {
        this.defaultTrainingDao = defaultTrainingDao;
    }

    public boolean isEditor(User user) {
        if (user.getRole() == 2) {
            return true;
        }
        return false;
    }

    public Training getTrainingByName(String name) {
        return this.getDefaultTrainingDao().getTrainingByName(name);
    }

    public List<Training> returnListOfTrainings() {
        return defaultTrainingDao.getAllTrainings();
    }

    public boolean checkTrainingConsistency(Training trainingToBeChecked) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String currentDateTime = dateFormat.format(calendar.getTime());

        String trainingStopTime = dateFormat.format(trainingToBeChecked.getStopDate());

        String trainingStartTime = dateFormat.format(trainingToBeChecked.getStartDate());
        System.out.println(trainingStartTime);
        System.out.println(trainingStopTime);
        if (trainingToBeChecked.getTrainingName().equals("")) {
            return false;

        } else if (trainingToBeChecked.getTrainerName().equals("")) {
            return false;

        } else if (currentDateTime.compareTo(trainingStopTime) >= 0) {
            return false;

        } else if (currentDateTime.compareTo(trainingStartTime) >= 0) {
            return false;

        } else if (trainingStartTime.compareTo(trainingStopTime) > 0) {
            return false;

        } else if (trainingToBeChecked.getGrade().equals("")) {
            return false;

        } else if (trainingToBeChecked.getTechnology().equals("")) {
            return false;

        } else if (trainingToBeChecked.getNumberPeople() <= 0) {
            return false;
        }
        return true;
    }

    public void addTraining(String trainingName,String trainerName, Date startDate, Date endDate, String grade, String technology, int places) {
        int id = getTrainingId();
        Training training = new Training(id,trainingName, trainerName, startDate, endDate, grade, technology, places);
        defaultTrainingDao.addTraining(training);
    }

    public void deleteSelectedTrainings(String[] ids) {
        for(String s: ids) {
            defaultEnrollmentDao.deleteTraining(Integer.parseInt(s));
            defaulRatingDao.deleteTraining(Integer.parseInt(s));
            defaultTrainingDao.deleteTraining(Integer.parseInt(s));
        }
    }

    public int getTrainingId(){
        List<Training> trainings = defaultTrainingDao.getAllTrainings();
        int min = 1;
        if(trainings.size()>0) {
            int max = trainings.get(trainings.size() - 1).getTrainingId();
            for (Training t : trainings) {
                if (min != t.getTrainingId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }

}

