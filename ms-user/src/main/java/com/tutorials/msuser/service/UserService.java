package com.tutorials.msuser.service;

import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.UserRsModel;

public interface UserService {
    UserRsModel createUser(SignupRequestModel request);
}
