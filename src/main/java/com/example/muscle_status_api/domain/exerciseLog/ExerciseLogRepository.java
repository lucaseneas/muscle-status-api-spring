package com.example.muscle_status_api.domain.exerciseLog;


import com.example.muscle_status_api.domain.exercise.Exercise;
import com.example.muscle_status_api.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExerciseLogRepository extends JpaRepository<ExerciseLog, Integer> {

    @Query(value = "SELECT * FROM exercise_log e WHERE e.user_id = ?1 AND e.exercise_id = ?2 AND SUBSTRING(e.log_date, 1, 11) = ?3",nativeQuery = true)
    List<ExerciseLog> findByUser_IdAndExercise_IdAndLogDateStartingWith(Integer user_id, Integer exercise_id, String log_date);

    List<ExerciseLog> findByUserAndExercise(User user, Exercise exercise);
}
