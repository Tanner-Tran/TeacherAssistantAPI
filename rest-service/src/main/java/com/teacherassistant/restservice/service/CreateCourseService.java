package com.teacherassistant.restservice.service;

import java.sql.SQLException;

import com.teacherassistant.restservice.dao.Database;
import com.teacherassistant.restservice.dto.Course;

public class CreateCourseService 
{
	public static boolean validateInput(Course course)
	{
		boolean valid = true;
		
		if (course.getCode() == null || course.getCode().equals(""))
		{
			valid = false;
		}
		
		if (course.getTitle() == null || course.getTitle().equals(""))
		{
			valid = false;
		}
		
		return valid;
	}
	
	public static boolean checkIfUsernameAvailable(String username) throws SQLException
	{
		return Database.checkIfUsernameAvailable(username);
	}
	
	public static boolean checkIfCourseCodeAvailable(String code, String teacher) throws SQLException
	{
		return Database.checkIfCourseCodeAvailable(code, teacher);
	}
	
	public static void createCourse(String teacher, String courseCode, String courseTitle) throws SQLException
	{
		Database.addCourse(courseTitle, courseCode, teacher);
	}
}
