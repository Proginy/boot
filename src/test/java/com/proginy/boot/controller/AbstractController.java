package com.proginy.boot.controller;

import org.junit.Before;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AbstractController
{

    protected MockMvc mockMvc;
    protected ObjectMapper objectMapper;

    @Before
    public void setUp()
    {

        objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }
}
