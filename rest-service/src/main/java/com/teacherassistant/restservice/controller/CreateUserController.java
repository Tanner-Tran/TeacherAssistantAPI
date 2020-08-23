package com.teacherassistant.restservice.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teacherassistant.restservice.dto.User;
import com.teacherassistant.restservice.service.CreateUserService;

@RestController
public class CreateUserController
{
	@PutMapping("/createUser")
	public ResponseEntity<String> createUser(@RequestBody User user) throws SQLException
	{
		HttpStatus status;
		
		if (CreateUserService.validateInput(user))
		{
			if (CreateUserService.checkIfUsernameAvailable(user))
			{
				CreateUserService.createUser(user);
				status = HttpStatus.CREATED;
			}
			else
			{
				status = HttpStatus.CONFLICT;
				
				return new ResponseEntity<>("Username taken", status);
			}
		}
		else
		{
			status = HttpStatus.BAD_REQUEST; // Will only happen if one of the fields supplied was empty/null 
		}
		
		return ResponseEntity.status(status).build();
	}
}