package com.proginy.boot.service;

import org.springframework.validation.annotation.Validated;

import com.proginy.boot.domain.entity.User;

@Validated
public interface UserService
{

    void createDefaultUser();

    User findByEmail(String email);
}
