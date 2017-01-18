package com.is.dao;

import com.is.model.Enrollment;

import java.util.List;

/**
 * Created by ctimbus on 8/3/2016.
 */
public interface EnrollmentDao {
    List<Enrollment> getAllEnrollments();

    int countRegisteredPeopleForATraining(int trainingId);

    void deleteTraining(int trainingId);
}
