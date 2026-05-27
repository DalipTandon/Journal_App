package com.journel.Journel.App;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class JournelAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(JournelAppApplication.class, args);
	}

	@Bean
	public PlatformTransactionManager addPlatformMananger(MongoDatabaseFactory dbFactory){
		return new MongoTransactionManager(dbFactory);
	}

}
