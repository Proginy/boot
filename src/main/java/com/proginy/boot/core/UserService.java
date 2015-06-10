package com.proginy.boot.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.proginy.boot.domain.entity.Role;
import com.proginy.boot.domain.entity.User;
import com.proginy.boot.domain.repository.RoleRepository;
import com.proginy.boot.domain.repository.UserRepository;

@Service
@Validated
public class UserService
{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public User createUser()
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
