package com.example.muscle_status_api.domain.workoutSessionExercise;

import com.example.muscle_status_api.domain.workoutSession.WorkoutSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutSessionExerciseRepository extends JpaRepository<WorkoutSessionExercise, Integer> {
    List<WorkoutSessionExercise> findByWorkoutSession(WorkoutSession workoutSession);
}
