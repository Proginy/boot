package com.proginy.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proginy.boot.domain.User;
import com.proginy.boot.domain.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUsername(String username)
    {
        User user = userRepository.findByUsername(username);

        return user;
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
