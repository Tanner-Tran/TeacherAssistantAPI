package com.teacherassistant.restservice.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teacherassistant.restservice.service.DeleteUserService;

@RestController
public class DeleteUserController 
{
	@DeleteMapping("/deleteUser")
	public ResponseEntity<String> deleteUser(@RequestParam (value = "username") String username, @RequestParam (value = "password") String password) throws SQLException
	{
		HttpStatus status;
		
		if (DeleteUserService.validInput(password))
		{
			if (DeleteUserService.checkIfUserExists(username, password)) // Username taken from cookie, password prompted for
			{
				DeleteUserService.deleteUser(username);
				status = HttpStatus.OK;		
			}
			else
			{
				status = HttpStatus.UNAUTHORIZED;
				return new ResponseEntity<>("Password incorrect", status); 
			}
		}
		else
		{
			status = HttpStatus.BAD_REQUEST;
		}		
		return ResponseEntity.status(status).build();
	}
}
