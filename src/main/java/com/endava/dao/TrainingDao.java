package com.endava.dao;

import com.endava.model.Training;

import java.util.List;

/**
 * Created by aaldea on 8/3/2016.
 */
public interface TrainingDao {

    List<Training> getAllTrainings();

    void deleteTraining(int trainingId);

    void addTraining(Training training);

   void updateTraining(Training trainingToBeUpdated);


    Training getTrainingByName(String name);

}
