package com.example.muscle_status_api.controllers;

import com.example.muscle_status_api.domain.exercise.Exercise;
import com.example.muscle_status_api.domain.exercise.ExerciseRepository;
import com.example.muscle_status_api.domain.exerciseLog.ExerciseLog;
import com.example.muscle_status_api.domain.exerciseLog.ExerciseLogRepository;
import com.example.muscle_status_api.domain.exerciseLog.RequestExerciseLog;
import com.example.muscle_status_api.domain.user.User;
import com.example.muscle_status_api.domain.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exercise-log")
public class ExerciseLogController {

    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ExerciseLogRepository exerciseLogRepository;

    @GetMapping
    public ResponseEntity findAll(){
        var findAll = exerciseLogRepository.findAll();
        return ResponseEntity.ok(findAll);
    }

    @GetMapping("/user-id/{userId}/exercise-id/{exerciseId}/date/{logDate}")
    public List<ExerciseLog> findByUserIdAndExerciseIdAndLogDate (@PathVariable(name="userId")Integer userId,
                                                 @PathVariable(name="exerciseId")Integer exerciseId,
                                                 @PathVariable(name="logDate")String logDate)
    {
        // Colocar um optionaçl
        return exerciseLogRepository.findByUserIdAndExerciseIdAndLogDate(userId,exerciseId,logDate);
    }

    @PostMapping("/user-id/{userId}/exercise-id/{exerciseId}")
    public ResponseEntity addExerciseLog (@PathVariable(name="userId")Integer userId,
                                          @PathVariable(name="exerciseId")Integer exerciseId,
                                          @RequestBody @Valid RequestExerciseLog body){

        Optional<User> optionalUser = userRepository.findById(userId);
        Optional<Exercise> optionalExercise = exerciseRepository.findById(exerciseId);
        if(optionalUser.isPresent() && optionalExercise.isPresent()){
            User user = optionalUser.get();
            Exercise exercise = optionalExercise.get();

            ExerciseLog exerciseLog = new ExerciseLog();
            exerciseLog.setSetNumber(body.setNumber());
            exerciseLog.setWeight(body.weight());
            exerciseLog.setRepetition(body.repetition());
            exerciseLog.setDescription(body.description());
            exerciseLog.setUser(user);
            exerciseLog.setExercise(exercise);
            exerciseLogRepository.save(exerciseLog);

            return ResponseEntity.ok(exerciseLog);
        }
        else {
            throw new EntityNotFoundException();
        }
    }





}
