package com.teacherassistant.restservice.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.teacherassistant.restservice.service.DeleteCourseService;

@RestController
public class DeleteCourseController 
{
	@DeleteMapping("/deleteCourse")
	public ResponseEntity<String> deleteCourse(@RequestParam (value = "username") String username, @RequestParam (value = "courseCode") String courseCode) throws SQLException
	{
		HttpStatus status;
		
		if (DeleteCourseService.checkIfUsernameAvailable(username))
		{
			status = HttpStatus.NOT_FOUND; 
			return new ResponseEntity<>("Given username does not exist", status); // In practice, this should never happen as the username will be fetched from the user's cookies
		}
		else
		{
			if (DeleteCourseService.checkIfCourseCodeAvailable(courseCode, username))
			{
				status = HttpStatus.NOT_FOUND;
				return new ResponseEntity<>("Given course code does not exist for given username", status); // In practice, this should never happen as the courseCode will be selected 
			}                                                                                               // by the user from a combobox of their courses
			else
			{
				status = HttpStatus.OK;
				DeleteCourseService.deleteCourse(username, courseCode);
			}
		}
		
		return ResponseEntity.status(status).build();
	}
}
