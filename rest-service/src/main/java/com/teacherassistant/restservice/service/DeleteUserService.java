package com.teacherassistant.restservice.service;

import java.sql.SQLException;

import com.teacherassistant.restservice.dao.Database;

public class DeleteUserService 
{
	public static boolean validInput(String password)
	{
		if (password == null || password.equals("")) // Username is guaranteed to be valid; don't need to check
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public static boolean checkIfUserExists(String username, String password) throws SQLException
	{
		return Database.validateLogin(username, password);
	}
	
	public static void deleteUser(String username) throws SQLException
	{
		Database.removeTeacherUser(username);
	}
}
