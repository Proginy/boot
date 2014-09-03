package com.proginy.boot.domain.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proginy.boot.domain.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>
{

    List<User> findByEmail(String email);
}