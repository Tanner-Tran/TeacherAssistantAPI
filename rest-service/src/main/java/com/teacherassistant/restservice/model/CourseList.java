package com.teacherassistant.restservice.model;

import java.util.List;

public class CourseList 
{
	private List<Course> courseList;
	
	public CourseList (List<Course> listIn)
	{
		courseList = listIn;
	}
	
	public List<Course> getCourseList()
	{
		return courseList;
	}
}
