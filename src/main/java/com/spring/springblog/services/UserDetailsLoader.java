package com.spring.springblog.services;

import com.spring.springblog.Repositories.UserRepository;
import com.spring.springblog.models.User;
import com.spring.springblog.models.UserWithRoles;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsLoader implements UserDetailsService {

    private final UserRepository userDao;

    public UserDetailsLoader(UserRepository userDao){
        this.userDao = userDao;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userDao.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("No user found for username: " + username);
        }
        UserDetails enhancedUser = new UserWithRoles(user);
        return enhancedUser;

    }
}
