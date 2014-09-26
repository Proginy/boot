package com.proginy.boot.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.proginy.boot.repository.RoleRepository;
import com.proginy.boot.repository.UserRepository;


@Component
@Transactional
public class Fixtures implements ApplicationListener<ContextRefreshedEvent>, Ordered
{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event)
    {
        if (event.getApplicationContext().getParent() == null)
        {
            configure();
        }
    }

    @Override
    public int getOrder()
    {
        return 0;
    }

    private void configure()
    {
        /*
        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        userRole.setDescription("Users that may edit their resumes");
        roleRepository.save(userRole);

        Role managerRole = new Role();
        managerRole.setName("ROLE_MANAGER");
        managerRole.setDescription("Users that may see and edit all resumes");
        roleRepository.save(managerRole);

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        adminRole.setDescription("Users that may add and delete other users");
        roleRepository.save(adminRole);

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword(passwordEncoder.encode("password"));
        adminUser.setEnabled(true);
        adminUser.addToRoles(adminRole);
        userRepository.save(adminUser);

        User managerUser = new User();
        managerUser.setUsername("manager");
        managerUser.setPassword(passwordEncoder.encode("password"));
        managerUser.setEnabled(true);
        managerUser.addToRoles(managerRole);
        userRepository.save(managerUser);

        User conventionalUser = new User();
        conventionalUser.setUsername("user");
        conventionalUser.setPassword(passwordEncoder.encode("password"));
        conventionalUser.setEnabled(true);
        conventionalUser.addToRoles(userRole);
        userRepository.save(conventionalUser);
        */
    }
}
