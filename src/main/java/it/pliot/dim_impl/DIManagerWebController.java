package it.pliot.dim_impl;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DIManagerWebController {
    public static void main(String[] args) {
        SpringApplication.run( DIManagerWebController.class, args);
    }
}