package BLL;

import java.util.ArrayList;
import DAL.InstructorDAL;

public class InstructorBLL {
	// tạo đối tượng DAL
	static InstructorDAL instructorDAL = new InstructorDAL();

	public ArrayList<CourseInstructor> getAllCourseInstructor() {
		return instructorDAL.getAllCourseInstructor();
	}

	public ArrayList<CourseInstructor> findByTitle(String title) {
		return instructorDAL.findByTitle(title);
	}

	public static String addInstructor(CourseInstructor p) {
		if (instructorDAL.addInstructor(p)) {
			return "Thêm thành công";
		}
		return "Thêm thất bại";
	}

	public static String editInstructor(CourseInstructor p) {
		if (instructorDAL.editInstructor(p)) {
			return "Cập nhật thành công";
		}
		return "Cập nhật thất bại";
	}

	public boolean checkCourseInstructor(int courseID) {
		return instructorDAL.checkCourseInstructor(courseID);
	}
}
