package com.teacherassistant.restservice.controller;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.teacherassistant.restservice.dto.StudentList;
import com.teacherassistant.restservice.service.GetStudentsService;

@RestController
public class GetStudentsController 
{
	@GetMapping("/students")
	public ResponseEntity<StudentList> getStudents(@RequestParam(value = "username") String username, @RequestParam(value = "courseCode") String courseCode) throws SQLException
	{
		if (GetStudentsService.validateInput(username, courseCode))
		{
			StudentList list = new StudentList(GetStudentsService.getStudentList(username, courseCode));
			
			return new ResponseEntity<StudentList>(list, HttpStatus.OK);
		}
		else
		{
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // In practice, this should never happen as the username will be fetched from the user's cookies
		}                                                               // and the courseCode will be selected by the user from a combobox of their courses
	}
}
