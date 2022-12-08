package com.tutorials.msuser.repository;

import com.tutorials.msuser.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default Optional<User> findByEmail(String email) {
        return findByEmailIgnoreCase(email);
    }

    Optional<User> findByEmailIgnoreCase(String email);
}
