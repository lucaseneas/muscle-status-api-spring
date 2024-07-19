CREATE TABLE exercise_log (
    id INT PRIMARY KEY AUTO_INCREMENT,
    weight DOUBLE ,
    repetition INT,
    set_number INT,
    log_date VARCHAR(50),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    exercise_id INT,
    FOREIGN KEY (exercise_id) REFERENCES exercise(id) ON DELETE CASCADE
);