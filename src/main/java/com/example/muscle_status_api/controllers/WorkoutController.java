package com.example.muscle_status_api.controllers;

import com.example.muscle_status_api.domain.user.User;
import com.example.muscle_status_api.domain.user.UserRepository;
import com.example.muscle_status_api.domain.workout.RequestWorkout;
import com.example.muscle_status_api.domain.workout.Workout;
import com.example.muscle_status_api.domain.workout.WorkoutRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/workout")
public class WorkoutController {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity getAllWorkout(){
        var allWorkout = workoutRepository.findAll();
        return ResponseEntity.ok(allWorkout);
    }

    @GetMapping("/user/{id}")
    public List<Workout> findWorkoutByIdUser(@PathVariable Integer id){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            return workoutRepository.findByUser(user);

        }
        else {
            throw new EntityNotFoundException();
        }

    }

    @PostMapping("/{id}")
    public ResponseEntity addWorkoutToUser(@RequestBody  RequestWorkout data, @PathVariable  Integer id){
        User user = userRepository.findById(id)
           .orElseThrow(() -> new IllegalArgumentException("Workout not found"));

        Workout workout = new Workout();
        workout.setName(data.name());
        workout.setDescription(data.description());
        workout.setUser(user);
        workoutRepository.save(workout);

        return ResponseEntity.ok(workout);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateWorkout(@RequestBody @Valid RequestWorkout body,@PathVariable Integer id){
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);
        if(optionalWorkout.isPresent()){
            Workout workout = optionalWorkout.get();
            workout.setName(body.name());
            workout.setDescription(body.description());

            return ResponseEntity.ok(workout);
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorkout(@PathVariable Integer id){
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);
        if(optionalWorkout.isPresent()){
            Workout workout = optionalWorkout.get();
            workoutRepository.delete(workout);

            return ResponseEntity.ok(workout);
        }
        else {
            throw new EntityNotFoundException();
        }
    }



}
