package com.example.muscle_status_api.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByEmail(String email);

    Optional<User> findById(Integer id);
}
