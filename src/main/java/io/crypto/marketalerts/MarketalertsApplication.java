package io.crypto.marketalerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableScheduling
public class MarketalertsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketalertsApplication.class, args);
    }

}
