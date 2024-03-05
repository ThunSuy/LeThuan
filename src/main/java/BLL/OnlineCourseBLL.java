package BLL;

import java.util.ArrayList;

import DAL.OnlineCourseDAL;

public class OnlineCourseBLL {
	OnlineCourseDAL onlineCourseDAL = new OnlineCourseDAL();

	public ArrayList<OnlineCourse> getAllOnlineCourse() {
		return onlineCourseDAL.getAllOnlineCourse();
	}

	public ArrayList<OnlineCourse> sortByCondition(String condition, boolean isAsc) {
		return onlineCourseDAL.sortByCondition(condition, isAsc);
	}

	public ArrayList<OnlineCourse> findOnlineCourse(String condition) {
		return onlineCourseDAL.findOnlineCourse(condition);
	}

	public String addOnlineCourse(OnlineCourse c) {
		if (onlineCourseDAL.addOnlineCourse(c)) {
			return "Thêm thành công";
		}
		return "Thêm thất bại";
	}

	public String editOnlineCourse(int courseID, String url) {
		if (onlineCourseDAL.editOnlineCourse(courseID, url)) {
			return "Edit thành công";
		}
		return "Edit thất bại";
	}

	public boolean delOnlineCourse(int courseid) {
		return onlineCourseDAL.delOnlineCourse(courseid);
	}
}
