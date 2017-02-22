package com.ndlan.setup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

//@SpringBootApplication
//@ComponentScan({"com.ndlanx.sys.web.rest."})
//@EnableAutoConfiguration()
//@ImportResource("classpath:/spring-config.xml")
public class ApplicationRestStartUp //extends SpringBootServletInitializer
{


	@Value("${tomcatport:8080}")
	private int port; 
	//@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ApplicationRestStartUp.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(ApplicationRestStartUp.class, args);
	}

}
