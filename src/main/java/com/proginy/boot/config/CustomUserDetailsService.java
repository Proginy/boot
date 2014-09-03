package com.proginy.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.proginy.boot.domain.User;
import com.proginy.boot.domain.model.SecurityUser;
import com.proginy.boot.service.UserService;


@Component
public class CustomUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException
    {
        User user = userService.findUserByUsername(userName);

        if (user == null)
        {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }

        return new SecurityUser(user);
    }
}

