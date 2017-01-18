package com.is.dao.mapper;

import com.is.model.Enrollment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Created by ctimbus on 8/3/2016.
 */
public class EnrollmentRowMapper implements RowMapper<Enrollment> {

    @Override
    public Enrollment mapRow(ResultSet rs, int rowNum) throws SQLException {
        Enrollment enrollment = new Enrollment();
        enrollment.setTrainingId(rs.getInt(1));
        enrollment.setUserId(rs.getInt(2));
        enrollment.setHasVoted(rs.getBoolean(3));
        return enrollment;
    }
}
