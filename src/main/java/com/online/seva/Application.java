package com.online.seva;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.online.seva")
public class Application {

    public static void main(String[] args) {
        //
        new SpringApplicationBuilder(Application.class)
                .application()
                .run(args);

    }
}