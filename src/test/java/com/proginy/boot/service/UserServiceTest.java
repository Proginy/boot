package com.proginy.boot.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.proginy.boot.domain.Role;
import com.proginy.boot.domain.User;
import com.proginy.boot.repository.RoleRepository;
import com.proginy.boot.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest
{

    private static final Long USER_ID = new Long("1");

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

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
