package com.endava.dao;

import com.endava.model.Rating;
import com.endava.model.Training;
import com.endava.model.User;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sprodan on 8/3/2016.
 */
public interface UserDao {
    List<User> getAllUsers();
    List<Training> employeeViewTrainings(User user);
    boolean registerEmployeeForUpcomingTraining(User user, String trainingName);
    float ratingForTraining(String nameOfTraining);
    HashMap<String, Rating> employeeViewRatingsForTrainings();
    boolean canEmployeeVote(User user, int trainingId);
    void registerEmployeeRating(int userId, int trainingId, int rate);
}
