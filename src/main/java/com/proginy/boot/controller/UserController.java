package com.proginy.boot.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proginy.boot.domain.User;
import com.proginy.boot.domain.UserRepository;


@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository user;

    @RequestMapping("test")
    public String test() {

        log.info("Test");
        return "OK";
    }

    @RequestMapping("user")
    public User getUser(@RequestParam("id") long id)
    {

        log.info("Get user");
        return user.findById(id);
    }
}
