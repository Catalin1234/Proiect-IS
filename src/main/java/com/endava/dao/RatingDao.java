package com.endava.dao;

import com.endava.model.Rating;
import com.endava.model.Training;
import com.endava.model.User;

import java.util.List;

/**
 * Created by aaldea on 8/3/2016.
 */
public interface RatingDao {
    List<Rating> getAllRatings();
    List<Rating> seeRatingForEveryTraining();
    void deleteTraining(int trainingId);
}
