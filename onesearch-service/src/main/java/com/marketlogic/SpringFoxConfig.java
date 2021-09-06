package com.marketlogic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).securitySchemes(basicScheme()).select()
				.apis(RequestHandlerSelectors.basePackage("com.marketlogic")).paths(PathSelectors.any()).build();
	}

	private List<SecurityScheme> basicScheme() {
		List<SecurityScheme> schemeList = new ArrayList<>();
		schemeList.add(new BasicAuth("basicAuth"));
		return schemeList;
	}
}
