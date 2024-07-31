package com.example.muscle_status_api.controllers;


import com.example.muscle_status_api.domain.workout.Workout;
import com.example.muscle_status_api.domain.workout.WorkoutRepository;

import com.example.muscle_status_api.domain.workoutSession.RequestWorkoutSession;
import com.example.muscle_status_api.domain.workoutSession.WorkoutSession;
import com.example.muscle_status_api.domain.workoutSession.WorkoutSessionRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/workout-session")
public class WorkoutSessionController {
    @Autowired
    WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    WorkoutRepository workoutRepository;

    @GetMapping
    public ResponseEntity findAll(){
        var allWorkoutSession = workoutSessionRepository.findAll();
        return ResponseEntity.ok(allWorkoutSession);
    }

    @GetMapping("/workout/{id}")
    public List<WorkoutSession> findWorkoutSessionByWorkoutId(@PathVariable Integer id){
        Optional<Workout> optionalWorkout = workoutRepository.findById(id);
        if(optionalWorkout.isPresent()){
            Workout workout = optionalWorkout.get();
            return workoutSessionRepository.findByWorkout(workout);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity addWorkoutSessionToWorkout(@Valid @RequestBody RequestWorkoutSession data, @PathVariable Integer id){
        Workout workout = workoutRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Workout Session not found"));

        WorkoutSession workoutSession = new WorkoutSession();
        workoutSession.setName(data.name());
        workoutSession.setDescription(data.description());
        workoutSession.setWorkout(workout);
        workoutSessionRepository.save(workoutSession);
        return ResponseEntity.ok(workoutSession);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateWorkoutSession(@RequestBody @Valid RequestWorkoutSession body, @PathVariable Integer id){
        Optional<WorkoutSession> optionalWorkoutSession = workoutSessionRepository.findById(id);
        if(optionalWorkoutSession.isPresent()){
            WorkoutSession workoutSession = optionalWorkoutSession.get();
            workoutSession.setName(body.name());
            workoutSession.setDescription(body.description());
            return ResponseEntity.ok(workoutSession);
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteWorkoutSession(@PathVariable Integer id){
        Optional<WorkoutSession> optionalWorkoutSession = workoutSessionRepository.findById(id);
        if(optionalWorkoutSession.isPresent()){
            WorkoutSession workoutSession = optionalWorkoutSession.get();
            workoutSessionRepository.delete(workoutSession);
            return ResponseEntity.ok(workoutSession);
        }
        else {
            throw new EntityNotFoundException();
        }
    }




}
