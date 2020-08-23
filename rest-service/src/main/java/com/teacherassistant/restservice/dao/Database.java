package com.teacherassistant.restservice.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.teacherassistant.restservice.dto.Student;

public class Database 
{
	private static Connection conn;
	
	// Helper functions start here
	
	public static void openDB() throws ClassNotFoundException, SQLException // Connect to DB
	{	
		String connectionURL = "jdbc:sqlserver://csc331.cqlujc9w3xkj.us-east-2.rds.amazonaws.com:1433;databaseName=TADB;user=admin;password=";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		conn = DriverManager.getConnection(connectionURL);
	}
	
	public static boolean checkIfUsernameAvailable(String username) throws SQLException
	{
		String query = "select* from TEACHER where username = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, username);
		
		ResultSet results = statement.executeQuery();
		
		return !results.next();
	}
	
	public static boolean checkIfCourseCodeAvailable(String code, String teacher) throws SQLException
	{
		String query = "select* from COURSE where code = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, code);
		statement.setString(2, teacher);
		
		ResultSet results = statement.executeQuery();
		
		return !results.next();
	}
	
	public static boolean checkIfStudentAlreadyAdded(String code, String studentID, String teacher) throws SQLException
	{
		String query = "select* from STUDENT where classCode = ? and teacher = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, code);
		statement.setString(2, teacher);
		statement.setString(3, studentID);
		
		ResultSet results = statement.executeQuery();
		
		return results.next();
	}
	
	public static boolean checkIfCourseHasStudents(String code, String teacher) throws SQLException
	{
		String query = "select* from STUDENT where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, code);
		statement.setString(2, teacher);
		
		ResultSet results = statement.executeQuery();
		
		return results.next();
	}
	
	public static boolean validateLogin(String username, String password) throws SQLException
	{
		String query = "select* from TEACHER where username = ? and userPass = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, username);
		statement.setString(2, password);
		
		ResultSet results = statement.executeQuery();
		
		return results.next();
	}
	
	public static boolean checkIfAttendanceDateAlreadyAdded(String classCode, String teacher, java.sql.Date attDate) throws SQLException
	{
		String query = "select* from ATTENDANCE where classCode = ? and teacher = ? and attDate = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setDate(3, attDate);
		
		ResultSet results = statement.executeQuery();
		
		return results.next();
	}
	
	public static boolean checkIfStudentWasAbsent(String classCode, String teacher, java.sql.Date attDate, String studentID) throws SQLException
	{
		String query = "select* from IS_ABSENT where classCode = ? and teacher = ? and attDate = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setDate(3, attDate);
		statement.setString(4, studentID);
		
		ResultSet results = statement.executeQuery();
		
		return results.next();
	}
	
	public static int getNumberOfMinorInfractions(String studentID, String classCode, String teacher) throws SQLException
	{
		String query = "select minor from INFRACTIONS where classCode = ? and teacher = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, studentID);
		
		ResultSet results = statement.executeQuery();
		
		results.next();
		return results.getInt(1);
	}
	
	public static int getNumberOfModerateInfractions(String studentID, String classCode, String teacher) throws SQLException
	{
		String query = "select moderate from INFRACTIONS where classCode = ? and teacher = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, studentID);
		
		ResultSet results = statement.executeQuery();
		
		results.next();
		return results.getInt(1);
	}
	
	public static int getNumberOfMajorInfractions(String studentID, String classCode, String teacher) throws SQLException
	{
		String query = "select major from INFRACTIONS where classCode = ? and teacher = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, studentID);
		
		ResultSet results = statement.executeQuery();
		
		results.next();
		return results.getInt(1);
	}
	
	public static boolean checkIfSeatingChartExists(String classCode, String teacher) throws SQLException
	{
		String query = "select* from SEATING_CHART where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		ResultSet results = statement.executeQuery();
		
		return results.next();
	}
	
	public static int getNumberOfRows(String classCode, String teacher) throws SQLException
	{
		String query = "select numRows from SEATING_CHART where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		ResultSet results = statement.executeQuery();
		
		results.next();
		return results.getInt(1);
	}
	
	public static int getNumberOfColumns(String classCode, String teacher) throws SQLException
	{
		String query = "select numCols from SEATING_CHART where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		ResultSet results = statement.executeQuery();
		
		results.next();
		return results.getInt(1);
	}
	
	public static boolean checkIfSeatTaken(String classCode, String teacher, int rowPos, int colPos) throws SQLException
	{
		String query = "select* from SEATING_ENTRY where classCode = ? and teacher = ? and rowPos = ? and colPos = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setInt(3, rowPos);
		statement.setInt(4, colPos);
		
		ResultSet results = statement.executeQuery();
		
		return results.next();
	}	
	
	// Helper functions end here
	
	public static void addTeacherUser(String lastName, String firstName, String username, String password) throws SQLException
	{	
		String query = "insert into TEACHER values (?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, lastName);
		statement.setString(2, firstName);
		statement.setString(3, username);
		statement.setString(4, password);
		
		statement.execute();
	}
	
	public static void removeTeacherUser(String username) throws SQLException
	{
		String query = "delete from TEACHER where username = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, username);
		
		statement.execute();
	}
	
	public static void addCourse(String title, String code, String teacher) throws SQLException
	{
		String query = "insert into COURSE values (?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, title);
		statement.setString(2, code);
		statement.setString(3, teacher);
		
		statement.execute();
	}
	
	public static void removeCourse(String code, String teacher) throws SQLException
	{
		String query = "delete from COURSE where code = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, code);
		statement.setString(2, teacher);
		
		statement.execute();
	}
	
	public static ResultSet getCourses(String teacher) throws SQLException
	{	
		String query = "SELECT * from COURSE where teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, teacher);
		
		return statement.executeQuery();
	}
		
	public static void addStudent(String lastName, String firstName, String studentID, String classCode, String teacher) throws SQLException
	{
		String query = "insert into STUDENT values (?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, lastName);
		statement.setString(2, firstName);
		statement.setString(3, studentID);
		statement.setString(4, classCode);
		statement.setString(5, teacher);
		
		statement.execute();
	}
	
	public static void removeStudent(String studentID, String classCode, String teacher) throws SQLException
	{
		String query = "delete from STUDENT where studentID = ? and classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, studentID);
		statement.setString(2, classCode);
		statement.setString(3, teacher);
		
		statement.execute();
	}
	
	public static void removeAllStudentsFromACourse(String classCode, String teacher) throws SQLException
	{
		String query = "delete from STUDENT where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		statement.execute();
	}
	
	public static String getStudent(String classCode, String teacher, String studentID) throws SQLException
	{		
		String query = "SELECT lastName, firstName, StudentID from STUDENT where teacher = ? and classCode = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, teacher);
		statement.setString(2, classCode);
		statement.setString(3, studentID);
		
		ResultSet rs = statement.executeQuery();
		
		String result = "";
		
		while (rs.next())
		{
			result += rs.getString("firstName");
			result += " ";
			result += rs.getString("lastName");
			result += " (";
			result += rs.getString("studentID");
			result += ")";
		}
		
		return result;
	}
	
	public static List<Student> getStudents(String classCode, String teacher) throws SQLException
	{
		ArrayList<Student> list = new ArrayList<>();
		
		String query = "SELECT lastName, firstName, StudentID from STUDENT where teacher = ? and classCode = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, teacher);
		statement.setString(2, classCode);
		
		ResultSet rs = statement.executeQuery();
		
		while (rs.next())
		{
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String studentID = rs.getString("studentID");
			
			Student result = new Student(lastName, firstName, studentID);
			list.add(result);
		}
		
		return list;
	}
	
	public static void addAttendanceDate(String classCode, String teacher, java.sql.Date attDate) throws SQLException
	{
		String query = "insert into ATTENDANCE values (?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setDate(3, attDate);
		
		statement.execute();
	}
	
	public static void removeAttendanceDate(String classCode, String teacher, java.sql.Date attDate) throws SQLException
	{
		String query = "delete from ATTENDANCE where classCode = ? and teacher = ? and attDate = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setDate(3, attDate);
		
		statement.execute();
	}
	
	public static void removeAllAttendanceDatesFromACourse(String classCode, String teacher) throws SQLException
	{
		String query = "delete from ATTENDANCE where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		statement.execute();
	}
	
	public static void addAbsent(String classCode, String teacher, java.sql.Date attDate, String studentID) throws SQLException
	{
		String query = "insert into IS_ABSENT values (?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setDate(3, attDate);
		statement.setString(4, studentID);
		
		statement.execute();
	}
	
	public static void removeAbsent(String classCode, String teacher, java.sql.Date Date, String studentID) throws SQLException
	{
		String query = "delete from IS_ABSENT where classCode = ? and teacher = ? and attDate = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setDate(3, Date);
		statement.setString(4, studentID);
		
		statement.execute();
	}
	
	public static void removeAllAbsencesOfAStudent(String classCode, String teacher, String studentID) throws SQLException
	{
		String query = "delete from IS_ABSENT where classCode = ? and teacher = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, studentID);
		
		statement.execute();
	}
	
	public static void removeAllAbsencesFromACourse(String classCode, String teacher) throws SQLException
	{
		String query = "delete from IS_ABSENT where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		statement.execute();
	}
	
	public static void addGradeType(String classCode, String teacher, String typeName, double typeWeight) throws SQLException
	{
		String query = "insert into GRADE_TYPE values (?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, typeName);
		statement.setDouble(4, typeWeight);
		
		statement.execute();
	}
	
	public static void removeGradeType(String classCode, String teacher, String typeName) throws SQLException
	{
		String query = "delete from GRADE_TYPE where classCode = ? and teacher = ? and typeName = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, typeName);
		
		statement.execute();
	}
	
	public static void addAssignment(String classCode, String teacher, String assignName, String assignType) throws SQLException
	{
		String query = "insert into ASSIGNMENT values (?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, assignName);
		statement.setString(4, assignType);
		
		statement.execute();
	}
	
	public static void removeAssignment(String classCode, String teacher, String assignName) throws SQLException
	{
		String query = "delete from ASSIGNMENT where classCode = ? and teacher = ? and assignName = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, assignName);
		
		statement.execute();
	}
	
	public static void addGrade(String classCode, String teacher, String assignName, String studentID, int grade) throws SQLException
	{
		String query = "insert into GRADE values (?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, assignName);
		statement.setString(4, studentID);
		statement.setInt(5, grade);
		
		statement.execute();
	}
	
	public static void removeGrade(String classCode, String teacher, String assignName, String studentID) throws SQLException
	{
		String query = "delete from GRADE where classCode = ? and teacher = ? and assignName = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, assignName);
		statement.setString(4, studentID);
		
		statement.execute();
	}
	
	public static void addInfractionsEntry(String classCode, String teacher, String studentID) throws SQLException
	{
		String query = "insert into INFRACTIONS values (?, ?, ?, default, default, default)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, studentID);
		statement.setString(2, classCode);
		statement.setString(3, teacher);
		
		statement.execute();
	}
	
	public static void removeInfractionsEntry(String classCode, String teacher, String studentID) throws SQLException
	{
		String query = "delete from INFRACTIONS where classCode = ? and teacher = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, studentID);
		
		statement.execute();
	}
	
	public static void removeAllInfractionEntriesFromACourse(String classCode, String teacher) throws SQLException
	{
		String query = "delete from INFRACTIONS where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		statement.execute();
	}
	
	public static void updateInfractions(String classCode, String teacher, String studentID, int minor, int moderate, int major) throws SQLException
	{
		String query = "update INFRACTIONS set minor = ?, moderate = ?, major = ? where classCode = ? and teacher = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setInt(1, minor);
		statement.setInt(2, moderate);
		statement.setInt(3, major);
		statement.setString(4, classCode);
		statement.setString(5, teacher);
		statement.setString(6, studentID);
		
		statement.execute();
	}
	
	public static void addSeatingChart(String classCode, String teacher, int numRows, int numCols) throws SQLException
	{
		String query = "insert into SEATING_CHART values (?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setInt(3, numRows);
		statement.setInt(4, numCols);
		
		statement.execute();
	}
	
	public static void removeSeatingChart(String classCode, String teacher) throws SQLException
	{
		String query = "delete from SEATING_CHART where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		statement.execute();
	}
	
	public static void addSeatingEntry(String classCode, String teacher, String studentID, int rowPos, int colPos) throws SQLException
	{
		String query = "insert into SEATING_ENTRY values (?, ?, ?, ?, ?)";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, studentID);
		statement.setInt(4, rowPos);
		statement.setInt(5, colPos);
		
		statement.execute();
	}
	
	public static void removeSeatingEntry(String classCode, String teacher, String studentID) throws SQLException
	{
		String query = "delete from SEATING_ENTRY where classCode = ? and teacher = ? and studentID = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setString(3, studentID);
		
		statement.execute();
	}
	
	public static void removeAllSeatingEntriesFromACourse(String classCode, String teacher) throws SQLException
	{
		String query = "delete from SEATING_ENTRY where classCode = ? and teacher = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		
		statement.execute();
	}
	
	public static String getStudentIDInSeat(String classCode, String teacher, int rowPos, int colPos) throws SQLException
	{
		String query = "select studentID from SEATING_ENTRY where classCode = ? and teacher = ? and rowPos = ? and colPos = ?";
		
		PreparedStatement statement = conn.prepareStatement(query);
		
		statement.setString(1, classCode);
		statement.setString(2, teacher);
		statement.setInt(3, rowPos);
		statement.setInt(4, colPos);
		
		ResultSet results = statement.executeQuery();
		
		results.next();
		return results.getString(1);
	}
}
