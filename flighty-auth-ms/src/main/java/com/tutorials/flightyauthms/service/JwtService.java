package com.tutorials.flightyauthms.service;

import com.tutorials.flightyauthms.model.ExtractJwtRqModel;
import com.tutorials.flightyauthms.model.ExtractJwtRsModel;
import com.tutorials.flightyauthms.model.GenerateJwtRqModel;
import com.tutorials.flightyauthms.model.GenerateJwtRsModel;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;

public interface JwtService {
    GenerateJwtRsModel generateToken(GenerateJwtRqModel requestBody);

    ExtractJwtRsModel extractToken(ExtractJwtRqModel requestBody);
}
