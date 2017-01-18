package com.is.services;

import com.is.dao.implementation.DefaultUserDao;
import com.is.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by catal on 1/18/2017.
 */
@Service
public class RegisterService {

    @Autowired
    private DefaultUserDao defaultUserDao;

    public void setDefaultUserDao(DefaultUserDao defaultUserDao) {
        this.defaultUserDao = defaultUserDao;
    }

    public boolean validUsername(String username) {

        if (defaultUserDao.validUserName(username)) {
            return true;
        }
        return false;
    }

    public User getUser() {
        return defaultUserDao.getUser();
    }

    public void addUser(String password, String userName, String firstName, String lastName, int role) {
        int id = getUserId();
        User user = new User(id, password, userName, firstName, lastName, role);
        defaultUserDao.registerUser(user);
    }

    public int getUserId(){
        List<User> users = defaultUserDao.getAllUsers();
        int min = 1;
        if(users.size()>0) {
            int max = users.get(users.size() - 1).getUserId();
            for (User u : users) {
                if (min != u.getUserId()) return min;
                min++;
            }
            return max + 1;
        } else {
            return 1;
        }
    }

}
