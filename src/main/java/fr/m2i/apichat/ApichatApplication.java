package fr.m2i.apichat;

import fr.m2i.apichat.service.ServiceInitDB;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApichatApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApichatApplication.class, args);
	}
	// Bean Initialisation des données
	//@Bean
	CommandLineRunner run(ServiceInitDB serviceInitDB) {
		return args -> {

			serviceInitDB.initCanaux();


		};
	}

}
