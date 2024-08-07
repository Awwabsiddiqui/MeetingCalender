package com.example.springrest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.example.springrest.entity.RepoEnt;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = RepoEnt.class)
@EnableTransactionManagement
@EnableAspectJAutoProxy()
public class SpringrestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringrestApplication.class, args);
	}
//https://freemarker.apache.org/docs/
	// https://freemarker.apache.org/docs/pgui_misc_servlet.html
}
