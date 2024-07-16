package com.example.muscle_status_api.domain.workoutSession;

import com.example.muscle_status_api.domain.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Integer> {

    List<WorkoutSession> findByWorkout(Workout workout);
}
