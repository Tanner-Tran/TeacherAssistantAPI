package com.teacherassistant.restservice.dto;

public class User 
{
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	
	public User(String firstNameIn, String lastNameIn, String usernameIn, String passwordIn)
	{
		firstName = firstNameIn;
		lastName = lastNameIn;
		username = usernameIn;
		password = passwordIn;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getLastName()
	{
		return lastName;
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
