package com.ma.rest.webservices.restfulwebservices;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWebServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWebServicesApplication.class, args);
	}
	
	@Bean
	public LocaleResolver localResolver() {
//		SessionLocaleResolver localResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver localResolver = new AcceptHeaderLocaleResolver();
		localResolver.setDefaultLocale(Locale.US);
		return localResolver;
	}
	
	// to read messages.prop files
	// we can set this in application.prop file
	/*public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		
		messageSource.setBasename("messages");
		return messageSource;
	}*/

}
