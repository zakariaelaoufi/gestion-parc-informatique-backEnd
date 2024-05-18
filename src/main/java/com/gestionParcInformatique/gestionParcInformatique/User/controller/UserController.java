package com.gestionParcInformatique.gestionParcInformatique.User.controller;

import com.gestionParcInformatique.gestionParcInformatique.User.authentication.RegisterRequest;
import com.gestionParcInformatique.gestionParcInformatique.User.model.User;
import com.gestionParcInformatique.gestionParcInformatique.User.repository.UserRepository;
import com.gestionParcInformatique.gestionParcInformatique.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;
    @GetMapping()
    public ResponseEntity<List<User>> getAllUser(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping()
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) {
        Optional<User> existingUser = userRepository.findByUserNumber(request.getUserNumber());

        if (existingUser.isPresent()) {
            return ResponseEntity.internalServerError().body("User with userNumber already exist.");
        }
        userService.createUser(request);
        return ResponseEntity.ok("User created successfully");

    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable long id){
        User user = userService.getUser(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
            User user = userService.getUser(id);
        if (user != null) {
            userService.deleteUser(id);
            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String,Object>> updateUser(@PathVariable long id, @RequestBody RegisterRequest user){
        User userExist = userService.getUser(id);
        Optional<User> existingUserNumber = userRepository.findByUserNumber(user.getUserNumber());

        if (existingUserNumber.isPresent() && existingUserNumber.get().getId() != id) {
            String message = "User with userNumber already exist.";

            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            return ResponseEntity.internalServerError().body(response);
        }
         if (userExist != null) {

            userExist = userService.updateUser(id,user);
            String message = "User updated successfully";

            Map<String, Object> response = new HashMap<>();
            response.put("user", userExist);
            response.put("message", message);

            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}