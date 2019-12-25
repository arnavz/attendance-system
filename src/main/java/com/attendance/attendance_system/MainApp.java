package com.attendance.attendance_system;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * Hello world!
 *
 */
@SpringBootApplication
public class MainApp {

	public static void main(String[] args) {
		SpringApplication app=new SpringApplication(MainApp.class);
		 app.setDefaultProperties(Collections
		          .singletonMap("server.port", "8083"));
		        app.run(args);
	}

}