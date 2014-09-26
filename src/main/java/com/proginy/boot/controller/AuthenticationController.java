package com.proginy.boot.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/authentication")
public class AuthenticationController
{

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login()
    {
        // This method will simply return status 200 if it is not intercepted by Spring Security
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public void logout(HttpServletRequest request) throws ServletException
    {
        request.logout();
    }

}
