package BLL;

import java.math.BigDecimal;
import java.util.ArrayList;

import DAL.ChartDAL;

public class ChartBLL {
	static ChartDAL chartDAL = new ChartDAL();
	
	public int getNumStudent() {
		return chartDAL.getNumStudent();
	}
	
	public int getNumInstructor() {
		return chartDAL.getNumInstructor();
	}
	public int getNumCourse() {
		return chartDAL.getNumCourse();
	}
	
	public int getNumOnlineCourse() {
		return chartDAL.getNumOnlineCourse();
	}
	
	public int getNumOnsiteCourse() {
		return chartDAL.getNumOnsiteCourse();
	}
	
	public int getUnassignedInstructorsCount() {
		return chartDAL.getUnassignedInstructorsCount();
	}
	
	public ArrayList<String> getUniqueCourseIDs(){
		ArrayList<Course> arrc = chartDAL.getAllCourse();
		ArrayList<Integer> arrid = chartDAL.getUniqueCourseIDs();
		ArrayList<String> arr = new ArrayList<String>();
		for (Course c : arrc) {
			for (Integer i : arrid) {
				if (c.getCourseID() == i) {
					arr.add(i+" - "+c.getTitle());
				}
			}
		}
		return arr;
	}
	
	public ArrayList<BigDecimal> getStudentIdByCourseID(int courseID) {
		ArrayList<StudentGrade> arrst = chartDAL.getStudentIdByCourseID(courseID);
		ArrayList<BigDecimal> arr = new ArrayList<BigDecimal>();
		
		for (StudentGrade stg : arrst) {
			arr.add(stg.getGrade());
		}
		
		return arr;
	}
	
	public int getNumCourseInstructor() {
		return chartDAL.getNumCourseInstructor();
	}
}
