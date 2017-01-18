package com.is.dao;

import com.is.model.Rating;

import java.util.List;

/**
 * Created by aaldea on 8/3/2016.
 */
public interface RatingDao {
    List<Rating> getAllRatings();
    List<Rating> seeRatingForEveryTraining();
    void deleteTraining(int trainingId);
}
