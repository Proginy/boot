package com.proginy.boot.domain.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.proginy.boot.domain.User;

public class SecurityUser extends User implements UserDetails
{

    private static final long serialVersionUID = 1L;

    public SecurityUser(User user)
    {
        if (user != null)
        {
            this.setId(user.getId());
            this.setFirstName(user.getFirstName());
            this.setLastName(user.getLastName());
            this.setUsername(user.getUsername());
            this.setPassword(user.getPassword());
            this.setRoles(user.getRoles());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        /*
        Set<Role> userRoles = this.getRoles();

        if (userRoles != null)
        {
            for (Role role : userRoles)
            {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());
                authorities.add(authority);
            }
        }
        */

        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("GUEST");
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword()
    {
        return super.getPassword();
    }

    @Override
    public String getUsername()
    {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
