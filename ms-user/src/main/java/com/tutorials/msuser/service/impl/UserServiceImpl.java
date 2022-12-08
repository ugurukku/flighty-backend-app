package com.tutorials.msuser.service.impl;

import static com.tutorials.msuser.mapper.UserMapper.USER_MAPPER_INSTANCE;

import com.tutorials.msuser.entity.User;
import com.tutorials.msuser.exception.AppException;
import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.UserRsModel;
import com.tutorials.msuser.repository.UserRepository;
import com.tutorials.msuser.repository.RoleRepository;
import com.tutorials.msuser.service.UserService;
import java.util.Collections;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public UserRsModel createUser(SignupRequestModel request) {
        checkDuplicateEmail(request.getUsername());

        var user = USER_MAPPER_INSTANCE.mapRequestToEntity(request);
        user.setPassword(encoder.encode(request.getPassword()));
        setRoles(user);
        userRepository.save(user);

        return USER_MAPPER_INSTANCE.mapEntityToResponse(user);
    }

    private void setRoles(User user) {
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new AppException("Role is 'ROLE_USER' not found"))));
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent())
            throw new AppException("User with this email already exists");
    }

}
