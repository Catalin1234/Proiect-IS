package com.is.dao.implementation;

import com.is.dao.RatingDao;
import com.is.dao.mapper.RatingRowMapper;
import com.is.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by aaldea on 8/3/2016.
 */

@Repository
public class DefaultRatingDao implements RatingDao {
    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Rating> getAllRatings() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Rating> listOfRatings = jdbcTemplate.query("select * from rating", new RatingRowMapper());
        return listOfRatings;
    }

    @Override
    public List<Rating> seeRatingForEveryTraining() {

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT trainingName,numberOfSubmits,overall,rating.trainingId\n" +
                "FROM training, rating\n" +
                "WHERE training.trainingId = rating.trainingId \n" +
                "GROUP BY trainingId;";
        List<Rating> listOfRating = jdbcTemplate.query(sql,new RatingRowMapper());

        return listOfRating;
    }

    @Override
    public void deleteTraining(int trainingId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("delete from rating where trainingId=" + trainingId +";");
    }


}
