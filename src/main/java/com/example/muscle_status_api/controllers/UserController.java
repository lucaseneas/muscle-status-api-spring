package com.example.muscle_status_api.controllers;

import com.example.muscle_status_api.domain.user.RequestUser;
import com.example.muscle_status_api.domain.user.User;
import com.example.muscle_status_api.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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

    @Transactional
    @PutMapping
    public ResponseEntity updateUser(@RequestBody @Valid RequestUser data) {
        Optional<User> optionalUser = repository.findById(data.id());
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            user.setName(data.name());
            user.setEmail(data.email());
            user.setPassword(data.password());
            return ResponseEntity.ok(user);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable @Valid Integer id){
        Optional<User> optionalUser = repository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            repository.delete(user);
            return ResponseEntity.ok("User "+user+" was deleted");
        }
        else {
            throw new EntityNotFoundException();
        }
    }




}
