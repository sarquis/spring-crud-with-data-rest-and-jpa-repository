package com.sqs.spring.employee.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    /*
     * Using CUSTOM TABLES for Authentication.
     */
    @Bean
    UserDetailsManager userDetailsManager(DataSource dataSource) {
	JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);

	theUserDetailsManager.setUsersByUsernameQuery("SELECT user_id, pw, active FROM members WHERE user_id = ?");

	theUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT user_id, role FROM roles WHERE user_id = ?");

	return theUserDetailsManager;
    }

    /*
     * USING DEFAULT SPRING SEC TABLES SCHEMA.
     */
    /*
     * @Bean UserDetailsManager userDetailsManager(DataSource dataSource) {
     * 
     * return new JdbcUserDetailsManager(dataSource); }
     */

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

	// @formatter:off
	http.authorizeHttpRequests(configurer ->
		configurer
			.requestMatchers(HttpMethod.GET, "/").hasRole("EMPLOYEE") // For Thymeleaf Demo
			.requestMatchers(HttpMethod.GET, "/css/**").hasRole("EMPLOYEE") // For Thymeleaf Demo
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
