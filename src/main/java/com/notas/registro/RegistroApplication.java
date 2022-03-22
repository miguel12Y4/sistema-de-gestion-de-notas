package com.notas.registro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegistroApplication {

	public static void main(String[] args) {
		System.out.println(args);
		SpringApplication.run(RegistroApplication.class, args);
	}

}
