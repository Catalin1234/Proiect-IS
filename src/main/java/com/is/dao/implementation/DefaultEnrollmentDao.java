package com.is.dao.implementation;

import com.is.dao.EnrollmentDao;
import com.is.dao.mapper.EnrollmentRowMapper;
import com.is.model.Enrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by ctimbus on 8/3/2016.
 */
@Repository
public class DefaultEnrollmentDao implements EnrollmentDao {
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Enrollment> getAllEnrollments() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        List<Enrollment> listOfEnrollments = jdbcTemplate.query("select * from enrollment", new EnrollmentRowMapper());
        return listOfEnrollments;
    }

    @Override
    public int countRegisteredPeopleForATraining(int trainingId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        String sql = "select count(*) from enrollment WHERE trainingId = " + trainingId;
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }


    @Override
    public void deleteTraining(int trainingId) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("delete from enrollment where trainingId=" + trainingId + ";");
    }

}
