package com.ezpark.io;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ezpark.io")
@EntityScan(basePackages = "com.ezpark.io")

public class App 
{
    public static void main(String[] args) { SpringApplication.run(App.class, args);}
}
