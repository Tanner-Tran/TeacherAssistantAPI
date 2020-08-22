package com.teacherassistant.restservice.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teacherassistant.restservice.dto.Course;
import com.teacherassistant.restservice.service.CreateCourseService;

@RestController
public class CreateCourseController 
{	
	@PutMapping("/createCourse")
	public ResponseEntity<String> createCourse(@RequestParam(value = "username") String username, @RequestBody Course course) throws SQLException
	{
		HttpStatus status;
		
		if (CreateCourseService.validateInput(course))
		{
			if (CreateCourseService.checkIfUsernameAvailable(username))
			{
				status = HttpStatus.NOT_FOUND;
			}
			else
			{
				if (CreateCourseService.checkIfCourseCodeAvailable(course.getCode(), username))
				{
					status = HttpStatus.CREATED;
					CreateCourseService.createCourse(username, course.getCode(), course.getTitle());
				}
				else
				{
					status = HttpStatus.CONFLICT;
					
					return new ResponseEntity<>("Course code taken", status);
				}
			}
		}
		else
		{
			status = HttpStatus.BAD_REQUEST;
		}
		
		return ResponseEntity.status(status).build();
	}
}
