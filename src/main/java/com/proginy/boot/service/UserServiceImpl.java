package com.proginy.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proginy.boot.domain.Role;
import com.proginy.boot.domain.User;
import com.proginy.boot.repository.RoleRepository;
import com.proginy.boot.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createuser()
    {
        Role adminRole = roleRepository.getOne(new Long("1"));

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setFirstName("Ny");
        adminUser.setLastName("Peang");
        adminUser.setPassword("password");
        adminUser.setEnabled(true);
        adminUser.addToRoles(adminRole);

        return userRepository.save(adminUser);
    }
}
