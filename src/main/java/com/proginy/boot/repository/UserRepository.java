package com.proginy.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proginy.boot.domain.User;

public interface UserRepository extends JpaRepository<User, Long>
{

    User findByUsername(String username);
}