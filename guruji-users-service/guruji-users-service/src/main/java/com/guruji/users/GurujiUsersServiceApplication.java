package com.guruji.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.guruji.commons.entity"})
@ComponentScan(basePackages = "com.guruji")
@EnableEurekaClient
public class GurujiUsersServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GurujiUsersServiceApplication.class, args);
	}

}
