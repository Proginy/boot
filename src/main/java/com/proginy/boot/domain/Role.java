package com.proginy.boot.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@Entity
@Table(name = "role")
public class Role
{

    @Id
    private long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    @JsonIgnore
    private Set<User> users = new HashSet<User>();
    
    public Role()
    {

    }

}
