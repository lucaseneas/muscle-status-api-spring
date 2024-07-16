package com.example.muscle_status_api.domain.workoutSessionExercise;

import com.example.muscle_status_api.domain.workoutSession.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutSessionExerciseRepository extends JpaRepository<WorkoutSessionExercise, Integer> {
    List<WorkoutSessionExercise> findByWorkoutSession(WorkoutSession workoutSession);
}
