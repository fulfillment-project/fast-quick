package com.fastquick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class FastquickApplication {

	public static void main(String[] args) {
		SpringApplication.run(FastquickApplication.class, args);
	}

}
