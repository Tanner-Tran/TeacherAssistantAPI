package com.teacherassistant.restservice.dto;

public class Student 
{
	private String lastName;
	private String firstName;
	private String studentID;
	
	public Student(String lastNameIn, String firstNameIn, String studentIDIn)
	{
		lastName = lastNameIn;
		firstName = firstNameIn;
		studentID = studentIDIn;
	}
	
	public String getLastName()
	{
		return lastName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String studentID()
	{
		return studentID;
	}
}
