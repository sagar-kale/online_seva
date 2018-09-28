package com.online.seva;

import com.online.seva.config.SpringApplicationContextInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@ComponentScan("com.online.seva")
public class Application {

    public static void main(String[] args) {
        //
        new SpringApplicationBuilder(Application.class)
                .initializers(new SpringApplicationContextInitializer())
                .application()
                .run(args);

    }
}