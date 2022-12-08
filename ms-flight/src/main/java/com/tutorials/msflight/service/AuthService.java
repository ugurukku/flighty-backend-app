package com.tutorials.msflight.service;

import com.tutorials.msflight.model.ExtractJwtRsModel;

public interface AuthService {

    ExtractJwtRsModel extractToken(String token);
}
