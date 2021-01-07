package io.crypto.marketalerts;

import io.crypto.marketalerts.model.Person;
import io.crypto.marketalerts.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
@Component
public class MarketalertsApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(MarketalertsApplication.class, args);
    }

// TODO: This can be removed it just provides some initial data on start up
    @Autowired
    private PersonRepository personRepository;

    @Override
    public void run(String... args) throws Exception {

        personRepository.save(Person.builder().id("1382050000").name("user").build());


    }
}
