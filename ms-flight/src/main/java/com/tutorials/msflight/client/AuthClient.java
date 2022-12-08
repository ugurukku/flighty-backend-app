package com.tutorials.msflight.client;


import static com.tutorials.msflight.util.Constants.EXTRACT_JWT_URL;

import com.tutorials.msflight.config.FeignClientConfig;
import com.tutorials.msflight.model.ExtractJwtRqModel;
import com.tutorials.msflight.model.ExtractJwtRsModel;
import com.tutorials.msflight.model.ResponseModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "auth-ms", url = "${client.url.auth}", configuration = FeignClientConfig.class)
public interface AuthClient {

    @PostMapping(EXTRACT_JWT_URL)
    ResponseEntity<ResponseModel<ExtractJwtRsModel>> extractToken(@RequestBody ExtractJwtRqModel requestBody);

}
