package com.teacherassistant.restservice.dto;

import java.util.List;

public class StudentList 
{
	private List<Student> studentList;
	
	public StudentList(List<Student> studentListIn)
	{
		studentList = studentListIn;
	}
	
	public List<Student> getStudentList()
	{
		return studentList;
	}
}
