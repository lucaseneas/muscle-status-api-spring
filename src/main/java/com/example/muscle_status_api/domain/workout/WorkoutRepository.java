package com.example.muscle_status_api.domain.workout;

import com.example.muscle_status_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Integer> {

    List<Workout> findByUser(User user);
}
