package com.is.dao.implementation;

import com.is.dao.UserDao;
import com.is.dao.mapper.EnrollmentRowMapper;
import com.is.dao.mapper.RatingRowMapper;
import com.is.dao.mapper.TrainingRowMapper;
import com.is.dao.mapper.UserRowMapper;
import com.is.model.Enrollment;
import com.is.model.Rating;
import com.is.model.Training;
import com.is.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import javax.sql.DataSource;
import java.util.*;

/**
 * Created by ctimbus on 8/3/2016.
 */
@Repository
public class DefaultUserDao implements UserDao {

    private static final String QUERY_1 = "select count(*) from user where username = '";
    private static final String QUERY_2 = "' and password = '";
    private static final String QUERY_3 = "select * from user where username = '";
    private static final String QUERY_4 = "' and password = '";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbcTemplate;

    private int exists;
    private User user;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public DataSource getDataSource() {
        return this.dataSource;
    }

    @Override
    public List<User> getAllUsers() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<User> listOfUsers = jdbcTemplate.query("select * from user", new UserRowMapper());
        return listOfUsers;
    }

    @Override
    public boolean registerEmployeeForUpcomingTraining(User user, String trainingNameToSeek) {
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        jdbcTemplate = new JdbcTemplate(dataSource);
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource("training", trainingNameToSeek);
        String sql = "SELECT * from training WHERE trainingName = :training";
        Training trainingToRegisterAt = namedParameterJdbcTemplate.queryForObject(sql, mapSqlParameterSource, new TrainingRowMapper());
        int userId = user.getUserId();
        int trainingId = trainingToRegisterAt.getTrainingId();
        String sqlCheckEnrollment = "select * from enrollment";
        List<Enrollment> listOfEnrollments = jdbcTemplate.query(sqlCheckEnrollment, new EnrollmentRowMapper());
        for (Enrollment enrollment : listOfEnrollments) {
            if (enrollment.getUserId() == userId && enrollment.getTrainingId() == trainingId) {
                System.out.println("Employee already registered");
                return false;
            }
        }
        //Inserting into the enrollment table
        String insertSql = "INSERT INTO enrollment " + "(trainingId,userId,hasVoted) VALUES (?,?,?)";
        jdbcTemplate.update(insertSql, new Object[]{trainingToRegisterAt.getTrainingId(), user.getUserId(), 0});
        System.out.println("We have enrolled the employee and increased the number of people");
        return true;
    }

    @Override
    public List<Training> employeeViewTrainings(User user) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String grade = user.getGrade();
        String sql = "select * from training where grade = '" + grade + "'";
        List<Training> listOfTrainings = jdbcTemplate.query(sql, new TrainingRowMapper());
        return listOfTrainings;
    }


    public boolean isValidUser(String username, String password) {
        jdbcTemplate = new JdbcTemplate(getDataSource());
        exists = jdbcTemplate.queryForObject(QUERY_1 + username + QUERY_2 + password + "';", Integer.class);
        if (exists == 1) {
            user = jdbcTemplate.queryForObject(QUERY_3 + username + QUERY_4 + password + "';", new UserRowMapper());
            System.out.println(user);
            return true;
        }
        return false;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public float ratingForTraining(String nameOfTraining) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT trainingID FROM training WHERE trainingName = '" + nameOfTraining + "'";
        int trainingID = jdbcTemplate.queryForObject(sql, Integer.class);
        String sql1 = "SELECT * FROM rating WHERE trainingId = " + trainingID;
        Rating rating = jdbcTemplate.queryForObject(sql1, new RatingRowMapper());

        return rating.getOverall();
    }

    @Override
    public HashMap<String, Rating> employeeViewRatingsForTrainings() {
        HashMap hashMap = new HashMap<String, Rating>();
        jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM rating";
        String sql1;
        String trainingName;
        List<Rating> ratings = jdbcTemplate.query(sql, new RatingRowMapper());
        for (Rating rating : ratings) {
            sql1 = "SELECT trainingName FROM training WHERE trainingID=" + rating.getTrainingId();
            trainingName = jdbcTemplate.queryForObject(sql1, String.class);
            hashMap.put(trainingName, rating);
        }
        return hashMap;
    }

    public boolean canEmployeeVote(User user, int trainingId) {
        String sql = "select hasVoted from enrollment where trainingId=" + trainingId +" and userId = " + user.getUserId()+";";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        System.out.println(user.getUserId());
        System.out.println(trainingId);
        String sqlCount = "select count(*) from enrollment where userId = " + user.getUserId()+" and trainingId = "+trainingId+";";

        int count = jdbcTemplate.queryForObject(sqlCount, Integer.class);
        System.out.println(count);
        if(count > 0) {
            int hasVoted = jdbcTemplate.queryForObject(sql, Integer.class);
            System.out.println(hasVoted);
            if (hasVoted == 1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    @Override
    public void registerEmployeeRating(int userId, int trainingId, int rate) {
        int oldNumberOfSubmits = 0;
        int newNumberOfSubmits = 1;
        float newOverall;
        String insertSql;
        jdbcTemplate = new JdbcTemplate(dataSource);
        NamedParameterJdbcTemplate namedParameterjdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

        String sql1 = "SELECT count(*) FROM rating WHERE trainingId =" + trainingId;
        exists = jdbcTemplate.queryForObject(sql1, Integer.class);

        if (exists == 1) {
            String sql = "SELECT * FROM rating WHERE trainingId =:tId";
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("tId", trainingId);
            Rating rating = namedParameterjdbcTemplate.queryForObject(sql, parameters, new RatingRowMapper());
            float oldOverall = rating.getOverall();
            oldNumberOfSubmits = rating.getNumberOfSubmits();
            newNumberOfSubmits = oldNumberOfSubmits + 1;
            newOverall = ((oldOverall * oldNumberOfSubmits) + rate) / newNumberOfSubmits;
            insertSql = "UPDATE rating SET numberOfSubmits=" + newNumberOfSubmits + ",overall=" + newOverall + " WHERE trainingId=" + trainingId +";";
            jdbcTemplate.update(insertSql);
        } else {
            newOverall = rate;
            insertSql = "INSERT INTO rating VALUES (?,?,?)";
            jdbcTemplate.update(insertSql, new Object[]{trainingId, newNumberOfSubmits, newOverall});
        }

        jdbcTemplate.update("update enrollment set hasVoted=1 where userId ="+userId + " and trainingId=" + trainingId);


    }

}

