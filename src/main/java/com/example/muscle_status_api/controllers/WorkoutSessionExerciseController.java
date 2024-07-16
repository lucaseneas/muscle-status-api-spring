package com.example.muscle_status_api.controllers;

import com.example.muscle_status_api.domain.exercise.Exercise;
import com.example.muscle_status_api.domain.exercise.ExerciseRepository;
import com.example.muscle_status_api.domain.workoutSession.WorkoutSession;
import com.example.muscle_status_api.domain.workoutSession.WorkoutSessionRepository;
import com.example.muscle_status_api.domain.workoutSessionExercise.WorkoutSessionExercise;
import com.example.muscle_status_api.domain.workoutSessionExercise.WorkoutSessionExerciseRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/workout-session-exercise")
public class WorkoutSessionExerciseController {

    @Autowired
    WorkoutSessionExerciseRepository workoutSessionExerciseRepository;

    @Autowired
    WorkoutSessionRepository workoutSessionRepository;

    @Autowired
    ExerciseRepository exerciseRepository;

    @GetMapping
    public ResponseEntity findAll(){
        var allWorkoutSessionExercise = workoutSessionExerciseRepository.findAll();
        return ResponseEntity.ok(allWorkoutSessionExercise);
    }

    @GetMapping("/workout-session/{id}")
    public List<WorkoutSessionExercise> findWorkoutSessionExerciseByWorkoutSession(@PathVariable Integer id){
        Optional<WorkoutSession> optionalWorkoutSession = workoutSessionRepository.findById(id);
        if(optionalWorkoutSession.isPresent()){
            WorkoutSession workoutSession = optionalWorkoutSession.get();
            return workoutSessionExerciseRepository.findByWorkoutSession(workoutSession);
        }
        else {
            throw new EntityNotFoundException();
        }
    }

    @PostMapping("/workout-session/{idWorkoutSession}/exercise/{idExercise}")
    public ResponseEntity addWorkoutSessionExercise(@PathVariable(name="idWorkoutSession") Integer idWorkoutSession,
                                                    @PathVariable(name="idExercise") Integer idExercise){
        Optional<WorkoutSession> optionalWorkoutSession = workoutSessionRepository.findById(idWorkoutSession);
        Optional<Exercise> optionalExercise = exerciseRepository.findById(idExercise);
        if(optionalExercise.isPresent() && optionalWorkoutSession.isPresent()){
            var workoutSession = optionalWorkoutSession.get();
            var exercise = optionalExercise.get();

            WorkoutSessionExercise workoutSessionExercise = new WorkoutSessionExercise();
            workoutSessionExercise.setWorkoutSession(workoutSession);
            workoutSessionExercise.setExercise(exercise);
            workoutSessionExerciseRepository.save(workoutSessionExercise);
            return ResponseEntity.ok(workoutSessionExercise);
        }
        else {
            throw new EntityNotFoundException();
        }
    }




}
