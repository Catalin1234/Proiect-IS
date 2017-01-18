package com.is.dao;

import com.is.model.User;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sprodan on 8/3/2016.
 */
public interface UserDao {
    List<User> getAllUsers();
    void registerUser(User user);
    boolean validUserName(String userName);
}
