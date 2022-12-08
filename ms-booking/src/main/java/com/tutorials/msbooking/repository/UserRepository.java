package com.tutorials.msbooking.repository;

import com.tutorials.msbooking.entity.User;
import com.tutorials.msbooking.enums.UserStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailAndStatus(String email, UserStatus status);
}
