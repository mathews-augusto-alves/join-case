package br.com.app.app_control;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "br.com.project.core.entities")
@EnableJpaRepositories(basePackages = "br.com.project.core.repository")
public class AppControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppControlApplication.class, args);
	}

}
