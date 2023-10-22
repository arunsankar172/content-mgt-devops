package com.mea.contentmanagement;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
//@EnableWebMvc
//public class CorsConfig implements WebMvcConfigurer {
//
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/content-management/**").allowedOrigins("*"); // Allow requests from any origin
//	}
//}

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/content-management/**") // Define the URL pattern for which CORS is allowed
				.allowedOrigins("http://localhost:3000/") // Specify the allowed origin (strict origin)
				.allowedMethods("GET", "POST", "PUT", "DELETE") // Specify allowed HTTP methods
				.allowedHeaders("*") // Allow all headers
				.allowCredentials(true); // Allow credentials (e.g., cookies)
	}
}