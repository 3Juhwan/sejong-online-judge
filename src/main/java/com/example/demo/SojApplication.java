package com.example.demo;

import com.example.demo.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class SojApplication {

	public static void main(String[] args) {
		SpringApplication.run(SojApplication.class, args);
	}

}
