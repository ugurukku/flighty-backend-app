package com.tutorials.flightyauthms.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {
    public static final String REQUEST_LOG_FORMAT = "Request data: [URL: {}, payload: {}]";
    public static final String RESPONSE_LOG_FORMAT = "Response data: [URL:{}, payload: {}]";

    public static final String EXTRACT_JWT_URL = "/jwt/extract";
    public static final String LOGIN_URL = "/login";

}
