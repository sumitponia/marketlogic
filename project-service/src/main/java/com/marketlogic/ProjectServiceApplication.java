package com.marketlogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableRetry
@EnableAsync
public class ProjectServiceApplication {

	@Autowired
	private ApplicationProperties applicationProperties;

	public static void main(String[] args) {
		SpringApplication.run(ProjectServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		if (applicationProperties.getEnableSecurity())
			restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(
					applicationProperties.getOnesearchUsername(), applicationProperties.getOnesearchPassword()));
		return restTemplate;
	}

}