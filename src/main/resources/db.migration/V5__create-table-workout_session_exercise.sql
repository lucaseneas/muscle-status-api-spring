CREATE TABLE workout_session_exercise (
    id INT PRIMARY KEY AUTO_INCREMENT,
    workout_session_id INT ,
    FOREIGN KEY (workout_session_id) REFERENCES workout_session(id) ON DELETE CASCADE,
    exercise_id INT,
    FOREIGN KEY (exercise_id) REFERENCES exercise(id) ON DELETE CASCADE
);