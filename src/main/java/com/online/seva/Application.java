package com.online.seva;

import com.online.seva.config.SpringApplicationContextInitializer;
import com.online.seva.config.listeners.SessionCounter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.servlet.ServletContextListener;


@SpringBootApplication
@EntityScan("com.online.seva.domain")
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        //
        new SpringApplicationBuilder(Application.class)
                .initializers(new SpringApplicationContextInitializer())
                /*      .listeners((ApplicationListener<?>) new SessionCounter())*/
                .application()
                .run(args);

    }

    @ApplicationScope
    @Bean
    public ServletListenerRegistrationBean<ServletContextListener> listenerRegistrationBean() {
        ServletListenerRegistrationBean<ServletContextListener> bean =
                new ServletListenerRegistrationBean<>();
        bean.setListener(new SessionCounter());
        return bean;

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}