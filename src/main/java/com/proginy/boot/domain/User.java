package com.proginy.boot.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Data
@Entity
@Table(name = "user")
public class User
{

    @Id
    @GeneratedValue
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private boolean enabled;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    @JsonManagedReference
    @JsonIgnore
    private Set<Role> roles = new HashSet<Role>();

    public User()
    {

    }

    public void addToRoles(Role role)
    {
        if (roles == null)
        {
            roles = new HashSet<Role>();
        }
        roles.add(role);
    }

    public void removeFromRoles(Role role)
    {
        roles.remove(role);
    }
}
