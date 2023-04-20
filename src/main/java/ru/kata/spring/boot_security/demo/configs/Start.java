package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.Set;

@Component
public class Start implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    private final UserService userService;

    public Start(RoleRepository roleRepository, UserService userService) {
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        if (roleRepository.findByNameRole(user.getRoleName()).isEmpty()) {
            roleRepository.save(user);
        }
        if (roleRepository.findByNameRole(admin.getRoleName()).isEmpty()) {
            roleRepository.save(admin);
        }
        Set<Role> role1 = Set.of(user);
        Set<Role> role2 = Set.of(admin);
        User user1 = new User("qwerty", "12345", "Ruslan" ,"Zaitsev","men",  role1);
        if (userService.findUserByUsername(user1.getUsername()).isEmpty()) {
            userService.save(user1, role1);
        }
        User user2 = new User("qwerty12", "56789", "Oleg", "Petrov","men" , role2);
        if (userService.findUserByUsername(user2.getUsername()).isEmpty()) {
            userService.save(user2, role2);
        }
    }
}
