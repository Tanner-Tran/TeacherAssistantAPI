package com.teacherassistant.restservice.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.teacherassistant.restservice.model.CourseList;
import com.teacherassistant.restservice.service.GetCoursesService;

@RestController
public class GetCoursesController 
{
	@GetMapping("/courses")
	public ResponseEntity<CourseList> getCourses(@RequestParam(value = "username") String username) throws SQLException
	{
		if (GetCoursesService.validateInput(username))
		{
			CourseList list = new CourseList(GetCoursesService.getCourses(username));
			
			return new ResponseEntity<CourseList>(list, HttpStatus.OK);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
}
