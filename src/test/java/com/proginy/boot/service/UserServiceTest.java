package com.proginy.boot.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.proginy.boot.core.UserService;
import com.proginy.boot.domain.entity.Role;
import com.proginy.boot.domain.entity.User;
import com.proginy.boot.domain.repository.RoleRepository;
import com.proginy.boot.domain.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest
{

    private static final Long USER_ID = new Long("1");

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService = new UserService();

    @Test
    public void createUser()
    {
        Role role = new Role();

        User user = new User();
        {
            user.setUsername("admin");
            user.setFirstName("Ny");
            user.setLastName("Peang");
            user.setPassword("password");
            user.setEnabled(true);
            user.addToRoles(role);
        }
        
        when(roleRepository.getOne(USER_ID)).thenReturn(role);

        userService.createUser();

        verify(roleRepository, times(1)).getOne(USER_ID);
        verify(userRepository, times(1)).save(user);
    }
}
