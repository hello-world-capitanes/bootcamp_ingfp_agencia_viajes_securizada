package com.babel.bootcamp.travels;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {


	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests((requests) -> {
			requests.requestMatchers("/travel-agency/public/**").permitAll();

			requests.requestMatchers(HttpMethod.PUT,"/travel-agency/admin/trips/**").hasRole("ADMIN");
			requests.requestMatchers(HttpMethod.POST,"/travel-agency/admin/trips/**").hasRole("ADMIN");
			requests.requestMatchers(HttpMethod.DELETE,"/travel-agency/admin/trips/**").hasRole("ADMIN");
			requests.requestMatchers(HttpMethod.GET, "/travel-agency/admin/trips/**").authenticated();

			requests.requestMatchers(HttpMethod.PUT,"/travel-agency/admin/hotels/**").hasRole("ADMIN");
			requests.requestMatchers(HttpMethod.POST,"/travel-agency/admin/hotels/**").hasRole("ADMIN");
			requests.requestMatchers(HttpMethod.DELETE,"/travel-agency/admin/hotels/**").hasRole("ADMIN");
			requests.requestMatchers(HttpMethod.GET, "/travel-agency/admin/hotels/**").authenticated();

			requests.requestMatchers("travel-agency/admin/users/**").hasRole("ADMIN");

			requests.anyRequest().authenticated();
		});

		http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
		http.httpBasic(Customizer.withDefaults());
		http.csrf(customizer -> customizer.disable());

		return http.build();


	}

	@Bean
	UserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
		//En BBDD tenemos myPassword para ambos casos
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		return jdbcUserDetailsManager;
	}


}
