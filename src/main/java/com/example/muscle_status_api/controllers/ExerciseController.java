package com.example.muscle_status_api.controllers;

import com.example.muscle_status_api.domain.exercise.Exercise;
import com.example.muscle_status_api.domain.exercise.ExerciseRepository;
import com.example.muscle_status_api.domain.exercise.RequestExercise;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {
    @Autowired
    ExerciseRepository exerciseRepository;

    @GetMapping
    public ResponseEntity findAll(){
        var exercise =exerciseRepository.findAll();
        return ResponseEntity.ok(exercise);
    }

    @PostMapping
    public ResponseEntity addExercise(@RequestBody RequestExercise body){
        Exercise exercise =new Exercise();
        exercise.setName(body.name());
        exercise.setDescription(body.description());
        exercise.setMuscle_group(body.muscle_group());

        exerciseRepository.save(exercise);

        return ResponseEntity.ok(exercise);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity updateExercise(@RequestBody RequestExercise body,@PathVariable Integer id){
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if(optionalExercise.isPresent()){
            Exercise exercise = optionalExercise.get();
            exercise.setName(body.name());
            exercise.setDescription(body.description());
            exercise.setMuscle_group(body.muscle_group());
            return ResponseEntity.ok(exercise);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity deleteExercise(@PathVariable Integer id){
        Optional<Exercise> optionalExercise = exerciseRepository.findById(id);
        if(optionalExercise.isPresent()){
            Exercise exercise = optionalExercise.get();
            exerciseRepository.delete(exercise);
            return ResponseEntity.ok(exercise);
        }
        else {
            throw new EntityNotFoundException();
        }
    }





}
