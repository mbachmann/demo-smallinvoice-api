package com.example.demosmallinvoiceapi;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@SpringBootApplication
@EnableFeignClients(
		basePackages = { "com.example.smallinvoicespringfeign.api"}
)
@ComponentScan({"com.example.smallinvoicespringfeign","com.example.demosmallinvoiceapi"})
public class DemoSmallApiApplication {

	@Autowired
	private Environment env;
	public static void main(String[] args) {
		SpringApplication.run(DemoSmallApiApplication.class, args);
	}

	@PostConstruct
	public void afterInit() {
		boolean hasDevProfile = Arrays.asList(env.getActiveProfiles()).contains("dev");
		boolean hasH2Database = Arrays.asList(env.getActiveProfiles()).contains("h2");
		String applicationName = env.getProperty("spring.application.name");
		String openApiInfo="";
		String h2ConsoleInfo="";
		if (hasDevProfile) {
			openApiInfo = "http://localhost:8080/v3/api-docs\n" +
					"http://localhost:8080/v3/api-docs.yaml -> yaml file is downloaded -> https://editor.swagger.io/\n" +
					"http://localhost:8080/swagger-ui.html \n";
		}
		if (hasH2Database) {
			h2ConsoleInfo= "http://localhost:8080/h2-console  " + "" +
					"-> mit Generic H2 (Embedded), org.h2.Driver, jdbc:h2:mem:testdb und sa \n";
		}
		System.out.println("\n\nApplication [" + applicationName + "] - Enter in Browser:\nhttp://localhost:8080 \n" +
				openApiInfo +
				h2ConsoleInfo + "\n" +
				"Active Profiles: " + Arrays.toString(env.getActiveProfiles()) + "\n\n");
	}



}
