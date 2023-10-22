package com.mea.contentmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import com.mea.contentmanagement.controller.ContentMgtExceptionHandler;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@Import(ContentMgtExceptionHandler.class)
public class ContentManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContentManagementApplication.class, args);
	}

}
