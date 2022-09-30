package fr.m2i.apichat;

import fr.m2i.apichat.service.ServiceInitDB;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class ApichatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApichatApplication.class, args);
	}

	// Bean Initialisation des données dans la base de données
	@Profile("!test") //Eviter de l'executer pendant le test
	@Bean
	CommandLineRunner run(ServiceInitDB serviceInitDB) {
		return args -> {

			serviceInitDB.initCanaux();
			serviceInitDB.initUsers();
			serviceInitDB.initMessages();


		};
	}
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurerAdapter() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:8080");
			}
		};
	}

}



