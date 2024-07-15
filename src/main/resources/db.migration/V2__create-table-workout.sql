CREATE TABLE workout (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255),
    user_id INT ,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);