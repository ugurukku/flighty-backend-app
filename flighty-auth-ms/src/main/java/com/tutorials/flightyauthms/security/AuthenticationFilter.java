package com.tutorials.flightyauthms.security;

import static com.tutorials.flightyauthms.util.Constants.LOGIN_URL;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.flightyauthms.exception.AppException;
import com.tutorials.flightyauthms.model.ErrorResponseModel;
import com.tutorials.flightyauthms.model.GenerateJwtRqModel;
import com.tutorials.flightyauthms.model.LoginRequestModel;
import com.tutorials.flightyauthms.service.JwtService;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Log4j2
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final JwtService jwtService;

    public AuthenticationFilter(AuthenticationManager authManager, JwtService jwtService) {
        this.jwtService = jwtService;
        super.setAuthenticationManager(authManager);
        super.setFilterProcessesUrl(LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            var login = objectMapper.readValue(request.getInputStream(), LoginRequestModel.class);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));
        } catch (IOException e) {
            throw new AppException("Login request body is not valid");
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException {
        var username = ((UserDetails) authResult.getPrincipal()).getUsername();
        var generateJwtRqModel = GenerateJwtRqModel.builder()
                .username(username)
                .rememberMe(true)
                .build();

        var jwtRsModel = jwtService.generateToken(generateJwtRqModel);
        generateResponse(response, HttpStatus.OK, jwtRsModel);
        log.info("Jwt token generated for: {}", username);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException {
        generateResponse(response, HttpStatus.BAD_REQUEST,
                ErrorResponseModel.builder()
                        .code(5555)
                        .message("Invalid credentials")
                        .build());
        log.info("Invalid credentials");
    }

    private void generateResponse(HttpServletResponse response, HttpStatus status, Object body) throws IOException {
        response.setStatus(status.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(
                response.getOutputStream(),
                ResponseEntity.status(status).body(body));
    }
}
