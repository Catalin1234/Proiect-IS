package com.is.dao.mapper;


import com.is.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ctimbus on 8/3/2016.
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt(1));
        user.setPassword(rs.getString(2));
        user.setUsername(rs.getString(3));
        user.setFirstName(rs.getString(4));
        user.setLastName(rs.getString(5));
        user.setRole(rs.getInt(6));
        user.setGrade(rs.getString(7));
        return user;
    }
}
