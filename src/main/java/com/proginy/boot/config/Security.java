package com.proginy.boot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.SecurityExpressionHandler;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;


/**
 * Configuration of Spring Security Created by owahlen on 05.01.14.
 */
@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class Security extends WebSecurityConfigurerAdapter
{

    // The UserDetailsService is used in the AuthenticationManagerBuilder below
    @Autowired
    private UserDetailsService applicationUserDetailsService;

    /**
     * Define a SHA-256 PasswordEncoder bean. It is used to configure the AuthenticationManagerBuilder below and to create the encrypted passwords for some
     * inital users in the Fixtures class.
     * 
     * @return StandardPasswordEncoder instance
     */
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new StandardPasswordEncoder();
    }

    /**
     * The RoleHierarchy indicates that some roles (e.g. ROLE_ADMIN) dominate other roles (e.g. ROLE_USER). This means that users that only have these other
     * roles have less rights than users with dominating roles. The role hierarchy bean is used in the SecurityExpression handler below.
     * 
     * @return RoleHierarchy instance
     */
    @Bean
    public RoleHierarchy roleHierarchy()
    {
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_MANAGER ROLE_MANAGER > ROLE_USER");
        return roleHierarchy;
    }

    /**
     * Define a bean that populates a DefaultWebSecurityExpressionHandler with a RoleHierarchy. It is used to configure WebSecurity and HttpSecurity below.
     * 
     * @return Instance of DefaultWebSecurityExpressionHandler
     */
    @Bean
    public SecurityExpressionHandler<FilterInvocation> securityExpressionHandler()
    {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setRoleHierarchy(roleHierarchy());
        return handler;
    }

    /**
     * Configure the way the AuthenticationManager will be constructed using an AuthenticationManagerBuilder.
     * 
     * @param auth
     *            AuthenticationManagerBuilder
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .userDetailsService(applicationUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * Expose the AuthenticationManager as a bean with the name authenticationManager
     * 
     * @return AuthenticationManager bean
     * @throws Exception
     */
    @Bean(name = "authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    /**
     * Configure WebSecurity
     * 
     * @param web
     *            WebSecurity instance
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.expressionHandler(securityExpressionHandler()); // reference the securityExpressionHandler with RoleHierarchy
    }

    /**
     * Configure HttpSecurity
     * 
     * @param http
     *            HttpSecurity instance
     * @throws Exception
     */
    @Override
    protected void configure(final HttpSecurity http) throws Exception
    {
        http
                .httpBasic() // basic authentication shall be used
                .withObjectPostProcessor(new ObjectPostProcessor<BasicAuthenticationFilter>()
                {

                    // The client also uses rememberMe cookies.
                    // However there is no convention in the HttpBasicConfigurer to associated the RememberMeServices of the
                    // HttpSecurity instance with the BasicAuthenticationFilter. This needs to be done
                    // manually in the postProcess callback.
                    @Override
                    public <O extends BasicAuthenticationFilter> O postProcess(O filter)
                    {
                        // fetch the RememberMeServices instance that has already been configured
                        // with HttpSecurity at the time postProcess is called.
                        RememberMeServices rememberMeServices = http.getSharedObject(RememberMeServices.class);
                        filter.setRememberMeServices(rememberMeServices);
                        return filter;
                    }
                });
        http
                .rememberMe(); // rememberMe cookies shall be honoured
        http
                .csrf().disable() // cross site request forgery checks are not possible with static pages
                .authorizeRequests() // all requests to URLs starting with "/api" shall be authenticated
                .antMatchers("/monitoring/**").hasRole("ADMIN")
                .antMatchers("/console/**").hasRole("ADMIN")
                .antMatchers("/api/**").authenticated()
                .anyRequest().permitAll() // all other URLs are allowed for anyone (e.g. static content)
                .expressionHandler(securityExpressionHandler()); // reference the securityExpressionHandler with RoleHierarchy
        http
                .headers().frameOptions().disable(); // H2 Console uses frames
    }

}