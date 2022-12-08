package com.tutorials.msuser.controller;

import static com.tutorials.msuser.util.UrlConstant.REQUEST_LOG_FORMAT;
import static com.tutorials.msuser.util.UrlConstant.RESPONSE_LOG_FORMAT;
import static com.tutorials.msuser.util.UrlConstant.SIGNUP_URL;

import com.tutorials.msuser.model.SignupRequestModel;
import com.tutorials.msuser.model.UserRsModel;
import com.tutorials.msuser.service.UserService;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping
public class UserController {
    private final UserService userService;

    @PostMapping(SIGNUP_URL)
    public ResponseEntity<UserRsModel> signup(@Valid @RequestBody SignupRequestModel request) {
        log.info(REQUEST_LOG_FORMAT, SIGNUP_URL, request);

        var response = userService.createUser(request);

        log.info(RESPONSE_LOG_FORMAT, SIGNUP_URL, response);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
