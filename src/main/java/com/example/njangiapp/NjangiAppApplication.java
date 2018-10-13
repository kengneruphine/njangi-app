package com.example.njangiapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication(scanBasePackages={"com.example.njangiapp"})
public class NjangiAppApplication  {

    public static void main(String[] args)
    {
        SpringApplication.run(NjangiAppApplication.class, args);
    }
}
