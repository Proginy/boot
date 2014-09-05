package com.proginy.boot.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "authority")
public class Authority
{

    @Id
    private long id;
    private String authority;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    public Authority()
    {

    }

}
