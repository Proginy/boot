package com.proginy.boot.utils.security;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.proginy.boot.domain.Role;
import com.proginy.boot.domain.User;
import com.proginy.boot.repository.UserRepository;


@Component
@Transactional
public class ApplicationUserDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String authName) throws UsernameNotFoundException
    {
        User user = userRepository.findByUsername(authName);
        if (user == null)
        {
            throw new UsernameNotFoundException("User not found: " + authName);
        }
        Collection<GrantedAuthority> authorities = loadAuthorities(user);
        return createUserDetails(user, authorities);
    }

    protected Collection<GrantedAuthority> loadAuthorities(User user)
    {
        // in this domain model a user only has a single role whereas Spring Security allows a n:m mapping
        Collection<Role> roles = user.getRoles();
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : roles)
        {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    protected UserDetails createUserDetails(User user, Collection<GrantedAuthority> authorities)
    {
        String username = user.getUsername();
        String password = user.getPassword();
        boolean enabled = user.isEnabled();
        boolean accountExpired = false;
        boolean passwordExpired = false;
        boolean accountLocked = false;

        return new ApplicationUser(username, password, enabled, !accountExpired, !passwordExpired, !accountLocked, authorities, user.getId());
    }
}
