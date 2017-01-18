package com.is.dao.implementation;

import com.is.dao.TrainingDao;
import com.is.dao.mapper.TrainingRowMapper;
import com.is.model.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Date;
import java.util.List;

/**
 * Created by aaldea on 8/3/2016.
 */

@Repository
public class DefaultTrainingDao implements TrainingDao {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Training> getAllTrainings() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Training> listOfTrainings = jdbcTemplate.query("select * from training", new TrainingRowMapper());
        return listOfTrainings;
    }

    @Override
    public void deleteTraining(int trainingId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String SQLCommand = "delete from training where trainingId = " + trainingId;
        jdbcTemplate.update(SQLCommand);
    }

    @Override
    public void addTraining(Training training) {
        String sql = "INSERT INTO training "
                + "(trainingId, trainingName,trainerName, startDate, stopDate, grade, technology, numberPeople) VALUES (?, ?, ?,?, ?, ?, ?, ?)";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sql,
                new Object[]{training.getTrainingId(), training.getTrainingName(), training.getTrainerName(), training.getStartDate(), training.getStopDate(), training.getGrade(), training.getTechnology(), training.getNumberPeople()});
    }

    @Override
    public void updateTraining(Training trainingToBeUpdated) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("UPDATE training SET " + "trainingName = ? , " +
                        "trainerName = ?," +
                        " startDate = ?, " +
                        " stopDate = ?," +
                        " grade = ? ," +
                        " technology= ?, " +
                        " numberPeople = ?" +
                        " WHERE trainingId= ? ",
                trainingToBeUpdated.getTrainingName(),
                trainingToBeUpdated.getTrainerName(),
                trainingToBeUpdated.getStartDate(),
                trainingToBeUpdated.getStopDate(),
                trainingToBeUpdated.getGrade(),
                trainingToBeUpdated.getTechnology(),
                trainingToBeUpdated.getNumberPeople(),
                trainingToBeUpdated.getTrainingId());
    }


    public Date returnTrainingStoppingDateByName(String trainingName) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sqlQuery = "select stopDate from training where trainingName = '" + trainingName + "';";

        return jdbcTemplate.queryForObject(sqlQuery, Date.class);
    }

    @Override
    public Training getTrainingByName(String name) {
        //System.out.println("The name is: " + name);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sqlQuery = "select * from training where trainingName = '" + name + "';";
        return jdbcTemplate.queryForObject(sqlQuery, new TrainingRowMapper());
    }

public Training searchTrainingById(int id){
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
    String sql ="Select * from training where trainingId = ?";
    Training training = jdbcTemplate.queryForObject(sql, new Object[]{id}, new TrainingRowMapper());
    return training;
}

}


