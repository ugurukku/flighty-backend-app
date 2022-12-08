package com.tutorials.msbooking.client;

import static com.tutorials.msbooking.util.Constants.EXTRACT_JWT_URL;

import com.tutorials.msbooking.model.ExtractJwtRqModel;
import com.tutorials.msbooking.model.ExtractJwtRsModel;
import com.tutorials.msbooking.model.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-ms", url = "${client.url.auth}")
public interface AuthClient {

    @PostMapping(EXTRACT_JWT_URL)
    ResponseEntity<ResponseModel<ExtractJwtRsModel>> extractToken(@RequestBody ExtractJwtRqModel requestBody);
}
