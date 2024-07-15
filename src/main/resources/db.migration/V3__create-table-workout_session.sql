CREATE TABLE workout_session (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    workout_id INT ,
    FOREIGN KEY (workout_id) REFERENCES workout(id) ON DELETE CASCADE
);