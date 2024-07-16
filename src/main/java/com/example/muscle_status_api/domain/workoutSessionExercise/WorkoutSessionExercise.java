package com.example.muscle_status_api.domain.workoutSessionExercise;

import com.example.muscle_status_api.domain.exercise.Exercise;
import com.example.muscle_status_api.domain.workoutSession.WorkoutSession;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name="workout_session_exercise")
@Entity(name="workout_session_exercise")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSessionExercise {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JoinColumn(name = "workout_session_id", referencedColumnName = "id")
    @ManyToOne
    private WorkoutSession workoutSession;

    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    @OneToOne
    private Exercise exercise;

}
