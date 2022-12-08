package com.tutorials.flightyauthms.service;

import com.tutorials.flightyauthms.model.UserAuthModel;
import java.util.Optional;

public interface UserService {

    Optional<UserAuthModel> findAuthModelByUsername(String username);

}
