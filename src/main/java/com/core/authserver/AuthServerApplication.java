package com.core.authserver;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//https://www.youtube.com/watch?v=783Igi3yT_U
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
@EnableResourceServer
@EnableEurekaClient
public class AuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServerApplication.class, args);
	}

	@GetMapping("/Test")
    public String Test(){
       return "running";
    }
}
