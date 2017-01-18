package com.is.dao.implementation;

import com.is.dao.UserDao;
import com.is.dao.mapper.UserRowMapper;
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
    public boolean validUserName(String userName) {
        for(User user : this.getAllUsers()){
            if (user.getUsername().equals(userName)){
                System.out.println("Exista deja");
                return false;
            }
        }
        System.out.println("username valid");
        return true;
    }
    @Override
    public void registerUser(User user){
        jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update("insert into user (`userId`, `password`, `userName`, `firstName`, `lastName`, `role`) values (?,?,?,?,?,?)",
                new Object[]{user.getUserId(),user.getPassword(),user.getUsername(),user.getFirstName(),user.getLastName(),user.getRole()});

    }

}

