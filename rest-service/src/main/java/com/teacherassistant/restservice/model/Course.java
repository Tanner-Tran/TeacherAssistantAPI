package com.teacherassistant.restservice.model;

public class Course 
{
	private String title;
	private String code;
	
	public Course(String titleIn, String codeIn)
	{
		title = titleIn;
		code = codeIn;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getCode()
	{
		return code;
	}
}
