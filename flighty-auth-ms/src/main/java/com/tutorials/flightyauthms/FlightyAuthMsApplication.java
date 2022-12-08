package com.tutorials.flightyauthms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FlightyAuthMsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightyAuthMsApplication.class, args);
    }

}
