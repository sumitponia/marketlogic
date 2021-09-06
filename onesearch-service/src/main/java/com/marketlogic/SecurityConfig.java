package com.marketlogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApplicationProperties applicationProperties;

	@Value("${spring.h2.console.enabled}")
	private Boolean h2ConsoleEnabled;

	protected void configure(HttpSecurity http) throws Exception {
		if (applicationProperties.getEnableSecurity()) {
			http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		} else {
			http.authorizeRequests().antMatchers("*/*").permitAll();
		}
		http.csrf().disable();
		if (h2ConsoleEnabled)
			http.headers().frameOptions().sameOrigin();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
				.antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/**","/swagger-ui/**"
						, "/webjars/**")
				.and().ignoring().antMatchers("/h2-console/**").and().ignoring().antMatchers("/error/**");
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder(11);
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(applicationProperties.getUserName())
				.password(this.encoder().encode(applicationProperties.getPassword())).roles("USER");
		applicationProperties.setUserName("");
		applicationProperties.setPassword("");
	}
}