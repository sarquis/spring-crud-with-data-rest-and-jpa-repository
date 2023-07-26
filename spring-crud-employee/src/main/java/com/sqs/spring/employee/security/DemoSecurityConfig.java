package com.sqs.spring.employee.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    // public no need
    JdbcUserDetailsManager userDetailsManager(DataSource dataSource) {

	return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	// @formatter:off
	http.authorizeHttpRequests(configurer ->
		configurer
			.requestMatchers(HttpMethod.GET, "/api/employees").hasRole("EMPLOYEE")
			.requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
			.requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
			.requestMatchers(HttpMethod.PUT, "/api/employees/**").hasRole("MANAGER")
			.requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
	);
	// @formatter:on

	http.httpBasic(Customizer.withDefaults());

	http.csrf(csrf -> csrf.disable());

	return http.build();
    }

}
