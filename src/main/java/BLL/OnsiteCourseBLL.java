package BLL;

import java.sql.Time;
import java.util.ArrayList;

import DAL.OnsiteCourseDAL;


public class OnsiteCourseBLL {
	OnsiteCourseDAL onsiteCourseDAL = new OnsiteCourseDAL();
	
	public ArrayList<OnsiteCourse> getAllOnsiteCourse(){
		return onsiteCourseDAL.getAllOnsiteCourse();
	}
	
	public ArrayList<OnsiteCourse> sortByConditon(String condition, boolean isAsc){
		return onsiteCourseDAL.sortByConditon(condition, isAsc);
	}
	
	public String addOnsiteeCourse(OnsiteCourse c) {
		if (onsiteCourseDAL.addOnsiteCourse(c)) {
			return "Thêm thành công";
		}
		return "Thêm thất bại";
	}

	public String editOnsiteCourse(int courseID, String location, String days, Time time) {
		if (onsiteCourseDAL.editOnsiteCourse(courseID, location, days, time)) {
			return "Edit thành công";
		}
		return "Edit thất bại";
	}

	public boolean delOnsiteCourse(int courseid) {
		return onsiteCourseDAL.delOnsiteCourse(courseid);
	}
}
