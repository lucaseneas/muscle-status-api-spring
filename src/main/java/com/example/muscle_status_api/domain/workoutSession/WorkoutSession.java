package com.example.muscle_status_api.domain.workoutSession;

import com.example.muscle_status_api.domain.workout.Workout;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RestController;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name="workout_session")
@RestController
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSession {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String created_at= ZonedDateTime.now(ZoneId.of("GMT")).toString();

    @JoinColumn(name="workout_id", referencedColumnName = "id")
    @ManyToOne
    private Workout workout;

}
