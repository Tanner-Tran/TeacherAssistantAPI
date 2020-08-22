package com.teacherassistant.restservice.dto;

public class Login 
{
	private String username;
	private String password;
	
	public Login(String usernameIn, String passwordIn)
	{
		username = usernameIn;
		password = passwordIn;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public String getPassword()
	{
		return password;
	}
}
