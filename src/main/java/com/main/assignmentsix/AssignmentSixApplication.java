package com.main.assignmentsix;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class AssignmentSixApplication {

    public class Message{
        public String message;

        public Message (String message){
            this.message = message;
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(AssignmentSixApplication.class, args);
    }

    @GetMapping("/")
    public Message hello(){
        return new Message("Hello, World!");
    }
}
