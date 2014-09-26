package com.proginy.boot.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "role")
public class Role
{

    @Id
    private long id;
    private String name;
    private String description;

    public Role()
    {

    }

}
