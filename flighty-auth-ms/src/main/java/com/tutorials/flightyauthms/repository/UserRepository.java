package com.tutorials.flightyauthms.repository;

import com.tutorials.flightyauthms.enums.UserStatus;
import com.tutorials.flightyauthms.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    default Optional<User> byUsernameAndStatus(String username, UserStatus status) {
        return findByEmailIgnoreCaseAndStatus(username, status);
    }

    Optional<User> findByEmailIgnoreCaseAndStatus(String email, UserStatus status);

}
