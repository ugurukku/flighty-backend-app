package com.tutorials.msflight.service.impl;

import com.tutorials.msflight.client.AuthClient;
import com.tutorials.msflight.model.ExtractJwtRqModel;
import com.tutorials.msflight.model.ExtractJwtRsModel;
import com.tutorials.msflight.service.AuthService;
import java.util.Objects;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthClient authClient;

    @Override
    public ExtractJwtRsModel extractToken(String token) {
        var extractTokenResponse = authClient.extractToken(ExtractJwtRqModel.builder().token(token).build());
        log.info("Extract token response: {}", extractTokenResponse);
        return Objects.requireNonNull(extractTokenResponse.getBody()).getData();
    }
}