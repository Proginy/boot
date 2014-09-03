package com.proginy.boot.service;

import org.springframework.validation.annotation.Validated;

import com.proginy.boot.domain.User;

@Validated
public interface UserService
{

    void createDefaultUser();

    User findUserByUsername(String username);
}
