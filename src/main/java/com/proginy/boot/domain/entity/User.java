package com.proginy.boot.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User
{

    @Id
    @GeneratedValue
    public long id;
    public String username;
    public String firstName;
    public String lastName;
    public String password;
    public Boolean enabled;

    public User()
    {

    }
}
