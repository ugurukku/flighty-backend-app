package com.tutorials.flightyauthms.service.impl;

import com.tutorials.flightyauthms.enums.UserStatus;
import com.tutorials.flightyauthms.model.UserAuthModel;
import com.tutorials.flightyauthms.repository.UserRepository;
import com.tutorials.flightyauthms.service.UserService;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepo;

    @Override
    public Optional<UserAuthModel> findAuthModelByUsername(String username) {
        return userRepo.byUsernameAndStatus(username, UserStatus.ACTIVE).map(UserAuthModel::new);
    }
}
