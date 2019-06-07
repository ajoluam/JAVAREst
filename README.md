# JAVAREst
Aplicação(sem front) desenvolvida seguindo orientação do site abaixo  -  
https://medium.com/assertqualityassurance/como-construir-e-testar-uma-api-em-java-utilizando-o-postman-baae69d8b8aa
Aplicação Rest
package com.montanha.gerenciador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration


public class GerenciadorViagensMontanhaApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(GerenciadorViagensMontanhaApplication.class, args);

	}

}
