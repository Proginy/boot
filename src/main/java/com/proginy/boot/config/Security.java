package com.proginy.boot.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


/**
 * Configuration of Spring Security Created by owahlen on 05.01.14.
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Security extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/css/**", "/js/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll();
    }

    //    @Override
    //    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    //    {
    //        JdbcUserDetailsManager userDetailsService = new JdbcUserDetailsManager();
    //        userDetailsService.setDataSource(datasource);
    //        PasswordEncoder encoder = new BCryptPasswordEncoder();
    //
    //        auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
    //        auth.jdbcAuthentication().dataSource(datasource);
    //
    //        if (!userDetailsService.userExists("user"))
    //        {
    //            //userService.createDefaultUser();
    //        }
    //    }

    //    @Configuration
    //    protected static class AuthenticationConfiguration extends GlobalAuthenticationConfigurerAdapter
    //    {
    //
    //        @Override
    //        public void init(AuthenticationManagerBuilder auth) throws Exception
    //        {
    //            auth
    //                    .inMemoryAuthentication()
    //                    .withUser("user").password("password").roles("USER");
    //        }
    //    }
    
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .jdbcAuthentication()
                .dataSource(dataSource)
                .withDefaultSchema()
                .withUser("user").password("password").roles("USER").and()
                .withUser("admin").password("password").roles("USER", "ADMIN");
    }

}