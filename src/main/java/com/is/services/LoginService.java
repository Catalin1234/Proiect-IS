package com.is.services;

import com.is.dao.implementation.DefaultUserDao;
import com.is.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sprodan on 8/3/2016.
 */
@Service
public class LoginService {

    @Autowired
    private DefaultUserDao defaultUserDao;

    public void setDefaultUserDao(DefaultUserDao defaultUserDao) {
        this.defaultUserDao = defaultUserDao;
    }

    public boolean loginUser(String username, String password) {
        if (defaultUserDao.isValidUser(username, password)) {
            return true;
        }
        return false;
    }

    public User getUser() {
        return defaultUserDao.getUser();
    }


}
