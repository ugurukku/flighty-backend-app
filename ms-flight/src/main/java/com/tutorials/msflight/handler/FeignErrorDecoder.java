package com.tutorials.msflight.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.msflight.exception.FailedToGetSuccessfulResponseException;
import com.tutorials.msflight.exception.FlightException;
import com.tutorials.msflight.model.ErrorResponseModel;
import com.tutorials.msflight.model.ResponseModel;
import feign.Response;
import feign.codec.ErrorDecoder;
import java.io.IOException;
import javax.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Log4j2
@ControllerAdvice
public class FeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        log.info("request : {}", response.request());
        if (response.status() == 400) {
            throw new ConstraintViolationException(getResponse(response), null);
        }
        if (response.status() == 404) {
            throw new FlightException(getResponse(response));
        }
        if (response.status() >= 400 && response.status() <= 599) {
            throw new FailedToGetSuccessfulResponseException(getResponse(response));
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

    private String getResponse(Response response) {
        var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            if (response.body() != null) {
                if (objectMapper.readValue(response.body().asReader(), ResponseModel.class).getData() instanceof ErrorResponseModel) {
                    return ((ErrorResponseModel) objectMapper.readValue(response.body().asReader(), ResponseModel.class).getData()).getMessage();
                } else {
                    return "Unexpected client error happened!";
                }
            }
        } catch (IOException e) {
            log.error("Exception details: ", e);
        }
        return null;
    }
}
