package com.spring.springblog.services;

import com.spring.springblog.models.User;
import com.spring.springblog.Repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository usersDao;


    public UserService(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    //Temporary placeholder
    // In the future we will replace this with the current logged in user
    public User getLoggedInUser(){
        return usersDao.findAll().get(0);
    }
}