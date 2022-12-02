package com.itbebop.documentation.springboot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication(scanBasePackages={"org.springframework.security.oauth2.client.userinfo"})
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }
}
