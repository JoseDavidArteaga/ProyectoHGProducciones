package co.edu.unicauca.distribuidos.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"co.edu.unicauca"})
@EnableJpaRepositories(basePackages = {"co.edu.unicauca"})
@EntityScan(basePackages = {"co.edu.unicauca"})
public class ProyectoApiRestClienteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProyectoApiRestClienteApplication.class, args);
	}

}
