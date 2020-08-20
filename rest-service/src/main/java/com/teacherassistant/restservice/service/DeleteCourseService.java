package com.teacherassistant.restservice.service;

import java.sql.SQLException;

import com.teacherassistant.restservice.dao.Database;

public class DeleteCourseService 
{
	public static boolean checkIfUsernameAvailable(String username) throws SQLException
	{
		return Database.checkIfUsernameAvailable(username);
	}
	
	public static boolean checkIfCourseCodeAvailable(String code, String teacher) throws SQLException
	{
		return Database.checkIfCourseCodeAvailable(code, teacher);
	}
	
	public static void deleteCourse(String username, String code) throws SQLException
	{
		Database.removeCourse(code, username);
	}
}
