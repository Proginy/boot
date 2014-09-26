package com.proginy.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proginy.boot.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{

    Role findByName(String name);
}
