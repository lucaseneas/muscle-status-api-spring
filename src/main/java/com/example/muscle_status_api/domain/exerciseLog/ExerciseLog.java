package com.example.muscle_status_api.domain.exerciseLog;

import com.example.muscle_status_api.domain.exercise.Exercise;
import com.example.muscle_status_api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name="exercise_log")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExerciseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer setNumber;
    private Double weight;
    private Integer repetition;
    private String log_date = ZonedDateTime.now(ZoneId.of("GMT")).toString();

    @JoinColumn(name="user_id", referencedColumnName = "id")
    @OneToOne
    private User user;

    @JoinColumn(name="exercise_id", referencedColumnName = "id")
    @OneToOne
    private Exercise exercise;

}
