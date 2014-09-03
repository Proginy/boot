package com.proginy.boot.service;

import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService
{

    void createDefaultUser();
}
