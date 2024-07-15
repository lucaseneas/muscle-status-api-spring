package com.example.muscle_status_api.domain.workout;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

}
