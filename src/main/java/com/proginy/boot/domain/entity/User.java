package com.proginy.boot.domain.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User
{

    @Id
    @GeneratedValue
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Boolean enabled;
    
    @ManyToMany(mappedBy = "user")
    private Set<Role> roles = new HashSet<Role>(0);

    public User()
    {

    }
}
