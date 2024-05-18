package com.gestionParcInformatique.gestionParcInformatique.User.service;

import com.gestionParcInformatique.gestionParcInformatique.User.authentication.RegisterRequest;
import com.gestionParcInformatique.gestionParcInformatique.User.model.User;
import com.gestionParcInformatique.gestionParcInformatique.User.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    public List<User> getUsers(){
        return repository.findAll();
    }
    public void createUser(RegisterRequest request) {
        User user = User.builder()
                .userNumber(request.getUserNumber())
                .fullName(request.getFullName())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();
        repository.save(user);
    }

    public Object getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.isAuthenticated())
            return auth.getPrincipal();

        return null;
    }

    public User getUser(long id){
        User user = repository.findUserById(id);
        if(!repository.existsById(id)){
                return null;
        }
        return user;
    }

    public void  deleteUser(long id){
        repository.deleteById(id);
    }

    public User updateUser(long id , RegisterRequest user){
        User existUser = repository.findUserById(id);
        if(existUser != null){
            existUser.setUserNumber(user.getUserNumber());
            existUser.setFullName(user.getFullName());
            existUser.setRole(user.getRole());
            if(user.getPassword() != null && !user.getPassword().isEmpty())
                existUser.setPassword(passwordEncoder.encode(user.getPassword()));
            repository.save(existUser);
            return existUser;
        }
        return null;
    }

}
