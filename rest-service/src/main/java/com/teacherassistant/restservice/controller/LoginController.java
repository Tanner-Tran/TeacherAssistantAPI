package com.teacherassistant.restservice.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.teacherassistant.restservice.model.Login;
import com.teacherassistant.restservice.service.LoginService;

@RestController
public class LoginController 
{
	@PutMapping("/login")
	public ResponseEntity<String> validateLogin(@RequestBody Login login) throws SQLException
	{
		HttpStatus status;
		
		if (LoginService.validateInput(login))
		{
			if (LoginService.validateLogin(login))
			{
				status = HttpStatus.ACCEPTED;
			}
			else
			{
				status = HttpStatus.UNAUTHORIZED;
			}
		}
		else
		{
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).build();
	}
}
