package com.proginy.boot;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.proginy.boot.config.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{

    @Autowired
    private DataSource datasource;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http
                .authorizeRequests()
                .antMatchers("/", "/home", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();
        http
                .formLogin().failureUrl("/login?error")
                .defaultSuccessUrl("/")
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder registry) throws Exception
    {
        registry.userDetailsService(customUserDetailsService);
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
}
