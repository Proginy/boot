package com.proginy.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.proginy.boot.domain.User;
import com.proginy.boot.domain.UserRepository;
import com.proginy.boot.domain.model.SecurityUser;


@Repository
public class UserDetailServiceImpl implements UserDetailsService
{

    @Autowired
    private UserRepository userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException
    {
        System.out.println("GETTING LOGIN DETAILS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("User: " + username);

        User user = userDao.findByUsername(username);

        /*
        if (user == null)
        {
            throw new UsernameNotFoundException("UserName " + username + " not found");
        }
        */
        
        /*
        List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
        gas.add(new GrantedAuthorityImpl("ROLE_USER"));
        UserDetails user = new org.springframework.security.core.userdetails.User(username, "password", true, true, true, true, gas);
        */

        return new SecurityUser(user);
    }
}
