package com.teacherassistant.restservice.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.teacherassistant.restservice.dao.Database;
import com.teacherassistant.restservice.model.Course;

public class GetCoursesService 
{
	public static boolean validateInput(String teacher) throws SQLException
	{
		boolean valid = true;
		
		if (teacher.equals("") || teacher == null || Database.checkIfUsernameAvailable(teacher))
		{
			valid = false;
		}
		
		return valid;
	}
	
	public static List<Course> getCourses(String teacher) throws SQLException
	{
		ArrayList<Course> courses = new ArrayList();
		
		ResultSet rs = Database.getCourses(teacher);
		
		while (rs.next())
		{
			String title = rs.getString("title");
			String code = rs.getString("code");
			
			courses.add(new Course(title, code));
		}
		
		return courses;
	}
}
