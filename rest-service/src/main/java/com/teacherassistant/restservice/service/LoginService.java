package com.teacherassistant.restservice.service;

import java.sql.SQLException;

import com.teacherassistant.restservice.dao.Database;
import com.teacherassistant.restservice.dto.Login;

public class LoginService 
{
	public static boolean validateInput(Login login)
	{
		boolean valid = true;
		
		if (login.getUsername() == null || login.getUsername().equals(""))
		{
			valid = false;
		}
		
		if (login.getPassword() == null || login.getPassword().equals(""))
		{
			valid = false;
		}
		
		return valid;
	}
	
	public static boolean validateLogin(Login login) throws SQLException
	{
		return Database.validateLogin(login.getUsername(), login.getPassword());
	}
}
