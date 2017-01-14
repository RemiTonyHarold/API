package com.remirobert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.handler.MappedInterceptor;

@SpringBootApplication
@EnableScheduling
public class ApiRssApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRssApplication.class, args);
	}
}
