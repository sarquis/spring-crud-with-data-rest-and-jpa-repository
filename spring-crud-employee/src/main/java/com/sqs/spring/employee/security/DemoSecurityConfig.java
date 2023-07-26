package com.sqs.spring.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    @Bean
    // public no need
    InMemoryUserDetailsManager userDetailsManager() {

	// @formatter:off
	UserDetails john = User.builder()
		.username("john")
		.password("{noop}test123")
		.roles("EMPLOYEE")
		.build();

	UserDetails mary = User.builder()
		.username("mary")
		.password("{noop}test123")
		.roles("EMPLOYEE", "MANAGER")
		.build();

	UserDetails susan = User.builder()
		.username("susan")
		.password("{noop}test123")
		.roles("EMPLOYEE", "MANAGER", "ADMIN")
		.build();
	// @formatter:on

	return new InMemoryUserDetailsManager(john, mary, susan);
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
