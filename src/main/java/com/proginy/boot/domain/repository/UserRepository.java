package com.proginy.boot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proginy.boot.domain.entity.User;

public interface UserRepository extends JpaRepository<User, Long>
{

    User findByUsername(String username);
}