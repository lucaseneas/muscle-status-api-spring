package com.example.muscle_status_api.domain.workoutSession;

import com.example.muscle_status_api.domain.workout.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkoutSessionRepository extends JpaRepository<WorkoutSession, Integer> {

    List<WorkoutSession> findByWorkout(Workout workout);
}
