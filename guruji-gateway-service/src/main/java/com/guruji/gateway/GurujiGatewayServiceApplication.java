package com.guruji.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableZuulProxy
@EnableEurekaServer
public class GurujiGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(GurujiGatewayServiceApplication.class, args);
	}

}
