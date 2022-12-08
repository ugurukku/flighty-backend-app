package com.tutorials.msexceladapter.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public final class Constants {
    public static final String REQUEST_LOG_FORMAT = "Request data: [URL: {}, payload: {}]";
    public static final String RESPONSE_LOG_FORMAT = "Response data: [URL:{}, payload: {}]";

    public static final String FILES_URL = "/files";

}
