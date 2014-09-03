package com.proginy.boot.domain.entity.key;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class UserAuthorityPk
{

    @Column(name = "username")
    private String username;

    @Column(name = "authority")
    private String authority;

}
