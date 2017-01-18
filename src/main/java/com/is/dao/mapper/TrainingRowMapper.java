package com.is.dao.mapper;

import com.is.model.Training;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by aaldea on 8/3/2016.
 */
public class TrainingRowMapper implements RowMapper<Training>{

    @Override
    public Training mapRow(ResultSet rs, int nrColumn) throws SQLException {
        Training t = new Training();
        t.setTrainingId(rs.getInt("trainingId"));
        t.setTrainingName(rs.getString("trainingName"));
        t.setTrainerName(rs.getString("trainerName"));
        t.setStartDate(rs.getDate("startDate"));
        t.setStopDate(rs.getDate("stopDate"));
        t.setGrade(rs.getString("grade"));
        t.setTechnology(rs.getString("technology"));
        t.setNumberPeople(rs.getInt("numberPeople"));
        return t;
    }
}
