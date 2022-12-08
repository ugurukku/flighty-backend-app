package com.tutorials.msbooking.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tutorials.msbooking.exception.BookingException;
import com.tutorials.msbooking.exception.FailedToGetSuccessfulResponseException;
import com.tutorials.msbooking.model.ErrorResponseModel;
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
            return new ConstraintViolationException(getResponse(response), null);
        }
        if (response.status() == 404) {
            return new BookingException(getResponse(response));
        }
        if (response.status() >= 400 && response.status() <= 599) {
            return new FailedToGetSuccessfulResponseException(getResponse(response));
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }

    private String getResponse(Response response) {
        var objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        try {
            if (response.body() != null) {
                return objectMapper.readValue(response.body().asReader(), ErrorResponseModel.class).getMessage();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}
