package com.tutorials.msbooking.service;

import com.tutorials.msbooking.model.ExtractJwtRsModel;

public interface AuthService {

    ExtractJwtRsModel extractToken(String token);
}
