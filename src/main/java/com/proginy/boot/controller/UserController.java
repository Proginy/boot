package com.proginy.boot.controller;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proginy.boot.domain.User;
import com.proginy.boot.repository.UserRepository;
import com.proginy.boot.service.UserService;


@RestController
@RequestMapping("user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @RequestMapping("test")
    public String test()
    {
        return "OK";
    }

    @RequestMapping("user")
    public User getUser(@RequestParam("id") long id)
    {
        return userRepository.findOne(id);
    }

    @RequestMapping("users")
    public List<User> getUsers(@RequestParam("ids") List<Long> ids)
    {
        log.info("Get Users");
        return userRepository.findAll(ids);
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public HttpEntity<User> createUser()
    {
        User user = userService.createUser();

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
