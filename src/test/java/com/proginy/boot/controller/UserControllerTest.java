package com.proginy.boot.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.proginy.boot.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest extends AbstractController
{

    private static final Long USER_ID = new Long(1);

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserController userController;

    @Override
    @Before
    public void setUp()
    {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void getUser() throws Exception
    {
        ResultActions resultActions = mockMvc.perform(
                get("/user/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("id", USER_ID.toString()));

        resultActions.andExpect(status().isOk());

        verify(userRepository, times(1)).findOne(USER_ID);

        verifyNoMoreInteractions(userRepository);
    }

}
