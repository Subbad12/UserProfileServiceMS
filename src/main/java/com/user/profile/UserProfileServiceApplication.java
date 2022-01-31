package com.user.profile;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @author Subba
 *
 */
@SpringBootApplication
@EnableCaching
public class UserProfileServiceApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserProfileServiceApplication.class, args);
	}

}
