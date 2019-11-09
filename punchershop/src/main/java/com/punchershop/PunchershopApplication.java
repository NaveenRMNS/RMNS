package com.punchershop;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author Naveen
 *
 */
@SpringBootApplication
public class PunchershopApplication {

	public static void main(String[] args) {
		SpringApplication.run(PunchershopApplication.class, args);
	}

	@Bean
	public CorsFilter corsFilter() {
		final List<String> allowedMethodsList = new ArrayList<String>();
		allowedMethodsList.add("OPTIONS");
		allowedMethodsList.add("GET");
		allowedMethodsList.add("POST");
		allowedMethodsList.add("PUT");
		allowedMethodsList.add("DELETE");
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedHeader("*");
		config.addAllowedOrigin("*");
		config.setAllowedMethods(allowedMethodsList);
		source.registerCorsConfiguration("/**", config);
		return new CorsFilter(source);
	}
	
}
