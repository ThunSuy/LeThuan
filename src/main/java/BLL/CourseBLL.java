package BLL;

import java.util.ArrayList;

import DAL.CourseDAL;


public class CourseBLL {
	CourseDAL courseDAL = new CourseDAL();
	
	public ArrayList<Course> getAllCourse(){
		return courseDAL.getAllCourse();
	}
	public ArrayList<String> getTitle(String name){
		return courseDAL.getTitle(name);
	}
	public ArrayList<Course> getAllCourseOnline(){
		return courseDAL.getAllCourseOnline();
	}
	
	public ArrayList<Course> getAllCourseOnsite(){
		return courseDAL.getAllCourseOnsite();
	}
	
	public int getCourseIDByTitleName(String name,String title){
		return courseDAL.getCourseIDByTitleName(name,title);
	}
	public ArrayList<Integer> getCbbCourse(){
		return courseDAL.getCbbCourse();
	}
	public ArrayList<Course> findCourseOnline(String condition){
		return courseDAL.findCourseOnline(condition);
	}
	public ArrayList<Course> findCombineSortOnline(String conditionSort, String conditionFind, boolean isAsc){
		return courseDAL.findCombineSortOnline(conditionSort, conditionFind, isAsc);
	}
	public ArrayList<Course> findCombineSortOnsite(String conditionSort, String conditionFind, boolean isAsc){
		return courseDAL.findCombineSortOnsite(conditionSort, conditionFind, isAsc);
	}
	
	public int newCourseID() {
		return courseDAL.newCourseID();
	}
	public ArrayList<Course> findCourseOnsite(String condition){
		return courseDAL.findCourseOnsite(condition);
	}
	public boolean addCourse(Course c) {
		return courseDAL.addCourse(c);
	}
	
	public boolean editCourse(int courseid, String title, int credits, int departmentid) {
		return courseDAL.editCourse(courseid, title, credits, departmentid);
	}

	public String delCourse(int courseid) {
		if (courseDAL.delCourse(courseid)) {
			return "Xóa thành công";
		}
		return "Xóa thất bại";
	}
}
