package com.example.technicaltest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.support.CronTrigger;

@SpringBootApplication

public class TechnicalTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(TechnicalTestApplication.class, args);
    }

    @Bean
    public CronTrigger cronTrigger() {
        return new CronTrigger("0 */5 * * * *");
    }
}








