package com.example.muscle_status_api.controllers;

import com.example.muscle_status_api.domain.user.RequestUser;
import com.example.muscle_status_api.domain.user.User;
import com.example.muscle_status_api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping
    public ResponseEntity getAllUsers(){
        var allUsers = repository.findAll();
        return ResponseEntity.ok(allUsers);
    }

    @PostMapping
    public ResponseEntity registerUser(@RequestBody @Valid RequestUser data){
        User newUser = new User(data);
        repository.save(newUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity uptadeUser(@PathVariable Long id, @RequestBody @Valid RequestUser data) {
        Optional<User> user = repository.findById(id);

        user.get().setName(data.name());
        user.get().setEmail(data.email());
        user.get().setPassword(data.password());
        System.out.println(user);
        return ResponseEntity.ok(user);
    }



}
