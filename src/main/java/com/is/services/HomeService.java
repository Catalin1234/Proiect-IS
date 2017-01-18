package com.is.services;

import com.is.dao.implementation.DefaultUserDao;
import com.is.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by blackghost on 1/18/2017.
 */
@Service
public class HomeService {
    @Autowired
    private DefaultUserDao defaultUserDao;

    public void setDefaultUserDao(DefaultUserDao defaultUserDao) {
        this.defaultUserDao = defaultUserDao;
    }
}
