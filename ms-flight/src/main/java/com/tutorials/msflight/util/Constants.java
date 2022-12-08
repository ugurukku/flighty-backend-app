package com.tutorials.msflight.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    public static final String REQUEST_LOG_FORMAT = "Request data: [URL: {}, payload: {}]";
    public static final String RESPONSE_LOG_FORMAT = "Response data: [URL:{}, payload: {}]";

    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm";

    public static final String EXTRACT_JWT_URL = "/jwt/extract";
    public static final String FLIGHTS_URL = "/flights";
    public static final String PATH_ID = "/{id}";

    public static final String ADMIN = "ADMIN";
}
