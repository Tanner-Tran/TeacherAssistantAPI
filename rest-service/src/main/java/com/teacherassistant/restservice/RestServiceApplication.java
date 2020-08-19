package com.teacherassistant.restservice;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.teacherassistant.restservice.dao.Database;

@SpringBootApplication
public class RestServiceApplication {

	public static void main(String[] args) throws ClassNotFoundException, SQLException 
	{
		Database.openDB();
		SpringApplication.run(RestServiceApplication.class, args);
	}

}
