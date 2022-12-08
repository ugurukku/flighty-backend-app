package com.tutorials.msuser.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@ToString
@Builder
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ResponseModel<T> {
    HttpStatus status;
    String dateTime;
    String service;
    T data;

    public ResponseModel(HttpStatus status, String dateTime, String service, T data) {
        this.status = status;
        this.dateTime = dateTime;
        this.service = service;
        this.data = data;
    }

    public static <T> ResponseModel<T> of(HttpStatus status, LocalDateTime dateTime, String service, T data) {
        return new ResponseModel<>(
                status, dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), service, data);
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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
