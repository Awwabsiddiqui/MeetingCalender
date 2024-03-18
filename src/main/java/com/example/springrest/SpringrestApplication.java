package com.example.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.springrest.entity.RepoEnt;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = RepoEnt.class)
@EnableTransactionManagement
public class SpringrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestApplication.class, args);
	}

}
