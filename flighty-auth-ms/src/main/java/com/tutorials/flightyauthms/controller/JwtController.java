package com.tutorials.flightyauthms.controller;

import static com.tutorials.flightyauthms.util.Constants.EXTRACT_JWT_URL;
import static com.tutorials.flightyauthms.util.Constants.REQUEST_LOG_FORMAT;
import static com.tutorials.flightyauthms.util.Constants.RESPONSE_LOG_FORMAT;

import com.tutorials.flightyauthms.model.ExtractJwtRqModel;
import com.tutorials.flightyauthms.model.ExtractJwtRsModel;
import com.tutorials.flightyauthms.service.JwtService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Log4j2
@Validated
@RequestMapping
public class JwtController {
    private final JwtService jwtService;

    @PostMapping(EXTRACT_JWT_URL)
    public ResponseEntity<ExtractJwtRsModel> extractToken(@Valid @RequestBody ExtractJwtRqModel requestBody) {
        log.info(REQUEST_LOG_FORMAT, EXTRACT_JWT_URL, requestBody);

        var response = jwtService.extractToken(requestBody);

        log.info(RESPONSE_LOG_FORMAT, EXTRACT_JWT_URL, response);
        return ResponseEntity.ok(response);
    }
}