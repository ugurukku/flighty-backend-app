package com.tutorials.msuser.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlConstant {
    public static final String REQUEST_LOG_FORMAT = "Request data: [Url: {}, payload: {}]";
    public static final String RESPONSE_LOG_FORMAT = "Response data: [Url: {}, payload: {}]";
    public static final String SIGNUP_URL = "/signup";
}
