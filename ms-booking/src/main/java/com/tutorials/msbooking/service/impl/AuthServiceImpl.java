package com.tutorials.msbooking.service.impl;

import com.tutorials.msbooking.client.AuthClient;
import com.tutorials.msbooking.model.ExtractJwtRqModel;
import com.tutorials.msbooking.model.ExtractJwtRsModel;
import com.tutorials.msbooking.service.AuthService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;

    @Override
    public ExtractJwtRsModel extractToken(String token) {
        var extractTokenResponse = authClient.extractToken(ExtractJwtRqModel.builder().token(token).build());
        log.info("Extract token response: {}", extractTokenResponse);
        return Objects.requireNonNull(extractTokenResponse.getBody()).getData();
    }
}