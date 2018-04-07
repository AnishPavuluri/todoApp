package com.todos.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 * Spring web application configuration class
 * @author Dell
 *
 */
@Configuration
@EnableWebMvc
@ImportResource({
    "classpath:/spring/spring-dao.xml"
})
@ComponentScan("com.todos")
public class AppConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/*").addResourceLocations("/js/");
	}

}
