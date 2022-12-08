package com.tutorials.flightyapigateway.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.http.HttpStatus;

public class ResponseModel<T> {
    private HttpStatus status;
    private String dateTime;
    private T data;

    public ResponseModel(HttpStatus status, String dateTime, T data) {
        this.status = status;
        this.dateTime = dateTime;
        this.data = data;
    }

    public static <T> ResponseModel<T> of(HttpStatus status, LocalDateTime dateTime, T data) {
        return new ResponseModel<>(
                status, dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), data);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
