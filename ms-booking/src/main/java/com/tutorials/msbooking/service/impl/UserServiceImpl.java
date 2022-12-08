package com.tutorials.msbooking.service.impl;

import com.tutorials.msbooking.entity.User;
import com.tutorials.msbooking.enums.UserStatus;
import com.tutorials.msbooking.exception.BookingException;
import com.tutorials.msbooking.repository.UserRepository;
import com.tutorials.msbooking.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User userByUsername(String username) {
        var user = userRepository.findByEmailAndStatus(username, UserStatus.ACTIVE)
                .orElseThrow(() -> new BookingException("User not found by given username"));

        log.info("User by username '{}': {}", username, user);
        return user;
    }
}
