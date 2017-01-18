package com.is.services;

import com.is.dao.implementation.DefaultEnrollmentDao;
import com.is.dao.implementation.DefaultTrainingDao;
import com.is.dao.implementation.DefaultUserDao;
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
 * Created by ctimbus on 8/5/2016.
 */
@Service
public class EmployeeService {

    @Autowired
    private DefaultUserDao defaultUserDao;
    @Autowired
    private DefaultTrainingDao defaultTrainingDao;
    @Autowired
    private DefaultEnrollmentDao defaultEnrollmentDao;

    private boolean tooManyPeople;

    public void setDefaultUserDao(DefaultUserDao defaultUserDao) {
        this.defaultUserDao = defaultUserDao;
    }

    public void setDefaultTrainingDao(DefaultTrainingDao defaultTrainingDao) {
        this.defaultTrainingDao = defaultTrainingDao;
    }

    public void setDefaultEnrollmentDao(DefaultEnrollmentDao defaultEnrollmentDao) {
        this.defaultEnrollmentDao = defaultEnrollmentDao;
    }

    public boolean isEmployee(User user) {
        if (user.getRole() == 1) {

            return true;
        }
        return false;
    }


    public boolean isUserConsistent(User user) {
        if ((user.getPassword() == null) || (user.getPassword().equals(""))) {
            return false;
        }
        if ((user.getUsername() == null) || (user.getUsername().equals(""))) {
            return false;
        }
        if ((user.getFirstName() == null) || (user.getFirstName().equals(""))) {
            return false;
        }
        if ((user.getLastName() == null) || (user.getLastName().equals(""))) {
            return false;
        }
        if ((user.getRole() != 1) || (user.getRole() != 2)) {
            return false;
        }
        if (user.getRole() == 1 && (user.getGrade().equals("") || user.getGrade() == null)) {
            return false;
        }
        return true;
    }

    public boolean isRegistrationDateValid(Date date) {
        ///Checking if the date is good
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        String currentDateTime = dateFormat.format(calendar.getTime());
        String trainingStopTime = date.toString();
        System.out.println(currentDateTime);
        if (currentDateTime.compareTo(trainingStopTime) > 0) {
            System.out.println("Training has already ended");
            return false;
        }
        return true;
    }

    public boolean registerEmployee(User user, String trainingName) {
        if (!isEmployee(user)) {
            System.out.println("Not an employee");
            return false;
        }
        Training training = defaultTrainingDao.getTrainingByName(trainingName);
        int trainingId = training.getTrainingId();
        int currentNumberOfRegisteredPeople = defaultEnrollmentDao.countRegisteredPeopleForATraining(trainingId);
        if (currentNumberOfRegisteredPeople == training.getNumberPeople()) {
            System.out.println("Too many ppl registered");
            tooManyPeople = true;
            return false;
        }
        if (!isRegistrationDateValid(defaultTrainingDao.returnTrainingStoppingDateByName(trainingName))) {
            System.out.println("Not a valid date");
            return false;
        }
        if (!defaultUserDao.registerEmployeeForUpcomingTraining(user, trainingName)) {
            System.out.println("Not able to register");
            return false;
        }
        System.out.println("Successfully registered the employee!!!");
        return true;
    }

    public boolean areTooManyPeople() {
        return tooManyPeople;
    }

    public List<Training> viewTraining(User user) {
        if (!isEmployee(user)) {
            System.out.println("Not an employee");
        } else {
            return defaultUserDao.employeeViewTrainings(user);
        }

        return null;
    }

    public boolean canEmployeeVote(User user, String trainingName) {
        //System.out.println("verific "+user +" " +trainingName);
        Training training = defaultTrainingDao.getTrainingByName(trainingName);
        //System.out.println("verific "+user +" " +training);
       return defaultUserDao.canEmployeeVote(user,training.getTrainingId());
    }
    public void registerEmployeeRating(int userId,String trainingName, int rate) {
        Training training = defaultTrainingDao.getTrainingByName(trainingName);
        defaultUserDao.registerEmployeeRating(userId,training.getTrainingId(),rate);
    }
}
