package com.exadel.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author Tsikhan Kuprevich
 * @since 10/5/2017
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.exadel")
public class HelloWorldConfiguration {
}