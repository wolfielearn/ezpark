package com.ezpark.io;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ezpark.io")
@EntityScan(basePackages = "com.ezpark.io")
@EnableKafka // 👈 MAKE SURE THIS IS PRESENT
public class Application
{
    public static void main(String[] args) { SpringApplication.run(Application.class, args);}
}
