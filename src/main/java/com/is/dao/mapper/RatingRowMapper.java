package com.is.dao.mapper;

import com.is.model.Rating;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by aaldea on 8/3/2016.
 */
public class RatingRowMapper implements RowMapper<Rating> {

    @Override
    public Rating mapRow(ResultSet rs, int rowNum) throws SQLException {
        Rating rating = new Rating();
        rating.setTrainingId(rs.getInt("trainingId"));
        rating.setNumberOfSubmits(rs.getInt("numberOfSubmits"));
        rating.setOverall(rs.getInt("overall"));
        return rating;
    }
}
