package com.proginy.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proginy.boot.domain.entity.User;
import com.proginy.boot.domain.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email)
    {
        List<User> users = userRepository.findByEmail(email);

        return users.get(0);
    }

    @Override
    public void createDefaultUser()
    {
        //        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        //        authorities.add(new SimpleGrantedAuthority("USER"));
        //        
        //        User user = new User.Builder()
        //                .username("user")
        //                .password("password")
        //                .build();

        //ser.setAuthorities(new HashSet<GrantedAuthority>(authorities));
    }
}
