package com.teacherassistant.restservice.service;

import java.sql.SQLException;

import com.teacherassistant.restservice.dao.Database;
import com.teacherassistant.restservice.model.User;

public class CreateUserService 
{
	public static boolean validateInput(User user)
	{
		boolean valid = true;
		
		if (user.getFirstName() == null || user.getFirstName().equals(""))
		{
			valid = false;
		}
		
		if (user.getLastName() == null || user.getLastName().equals(""))
		{
			valid = false;
		}
		
		if (user.getPassword() == null || user.getPassword().equals(""))
		{
			valid = false;
		}
		
		if (user.getUsername() == null || user.getUsername().equals(""))
		{
			valid = false;
		}
		
		return valid;
	}
	
	public static boolean checkIfUsernameAvailable(User user) throws SQLException
	{
		return Database.checkIfUsernameAvailable(user.getUsername());
	}
	
	public static void createUser(User user) throws SQLException
	{
		Database.addTeacherUser(user.getLastName(), user.getFirstName(), user.getUsername(), user.getPassword());
	}
}
