package BLL;

import java.math.BigDecimal;

import javax.swing.JTextField;

public class StudentGrade {
	private int enrollmentID;
	private int courseID;
	private int studentID;
	private BigDecimal grade;

	public StudentGrade(int enrollmentID, int courseID, int studentID, BigDecimal grade) {
		this.enrollmentID = enrollmentID;
		this.courseID = courseID;
		this.studentID = studentID;
		this.grade = grade;
	}

	public StudentGrade() {
	}

	public int getEnrollmentID() {
		return enrollmentID;
	}

	public void setEnrollmentID(int enrollmentID) {
		this.enrollmentID = enrollmentID;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public BigDecimal getGrade() {
		return grade;
	}

	public void setGrade(BigDecimal grade) {
		this.grade = grade;
	}

}
