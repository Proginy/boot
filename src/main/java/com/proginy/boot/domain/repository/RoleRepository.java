package com.proginy.boot.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proginy.boot.domain.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>
{

    Role findByName(String name);
}
