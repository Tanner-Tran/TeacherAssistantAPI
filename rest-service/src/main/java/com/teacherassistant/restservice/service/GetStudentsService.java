package com.teacherassistant.restservice.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teacherassistant.restservice.dao.Database;
import com.teacherassistant.restservice.dto.Student;

public class GetStudentsService 
{
	public static boolean validateInput(String username, String courseCode)
	{
		boolean valid = true;
		
		if (username == null || username.equals(""))
		{
			valid = false;
		}
		
		if (courseCode == null || courseCode.equals(""))
		{
			valid = false;
		}
		
		return valid;
	}
	
	public static List<Student> getStudentList(String username, String courseCode) throws SQLException
	{
		return Database.getStudents(courseCode, username);
	}
}
