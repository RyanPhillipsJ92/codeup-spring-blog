package com.spring.springblog.services;

import com.spring.springblog.models.User;
import com.spring.springblog.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository usersDao;


    public UserService(UserRepository usersDao) {
        this.usersDao = usersDao;
    }

    public User getLoggedInUser(){
     User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
     long userId = user.getId();
     return usersDao.findById(userId).get();
    }
}