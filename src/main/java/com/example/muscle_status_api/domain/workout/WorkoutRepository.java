package com.example.muscle_status_api.domain.workout;

import org.hibernate.jdbc.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutRepository extends JpaRepository<Workout, Integer> {
}
