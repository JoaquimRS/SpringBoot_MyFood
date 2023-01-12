package com.myfood.springboot_myfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@SpringBootApplication
public class SpringbootMyfoodApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMyfoodApplication.class, args);
	}

}
