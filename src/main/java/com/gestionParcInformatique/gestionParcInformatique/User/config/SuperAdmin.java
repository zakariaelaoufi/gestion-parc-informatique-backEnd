package com.gestionParcInformatique.gestionParcInformatique.User.config;


import com.gestionParcInformatique.gestionParcInformatique.User.model.Role;
import com.gestionParcInformatique.gestionParcInformatique.User.model.User;
import com.gestionParcInformatique.gestionParcInformatique.User.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class SuperAdmin implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var exist = userRepository.findByFullName("super admin");
        if ( exist == null) {
            User user = User.builder()
                            .userNumber("00000")
                            .fullName("super admin")
                            .role(Collections.singletonList(Role.SUPER_ADMIN))
                            .password(passwordEncoder.encode("Aa123456")).build();
            userRepository.save(user);
        }
    }
}
