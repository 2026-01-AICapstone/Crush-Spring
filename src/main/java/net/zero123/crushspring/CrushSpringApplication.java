package net.zero123.crushspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class CrushSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrushSpringApplication.class, args);
    }

}
