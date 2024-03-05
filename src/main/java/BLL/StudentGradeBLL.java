package BLL;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;

import DAL.StudentGradeDAL;

public class StudentGradeBLL {
	StudentGradeDAL gradeDal = new StudentGradeDAL();

	public ArrayList<StudentGrade> getStudentIdByCourseID(int courseId) {
		return gradeDal.getStudentIdByCourseID(courseId);
	}

	public ArrayList<StudentGrade> getStudentByGrade(int courseID, double grade1, double grade2) {
		return gradeDal.getStudentByGrade(courseID, grade1, grade2);
	}

	public ArrayList<StudentGrade> getStudentGradeIsnull(int courseID) {
		return gradeDal.getStudentGradeIsnull(courseID);
	}

	public ArrayList<StudentGrade> getFindByGrade(int courseID, double grade) {
		return gradeDal.getFindByGrade(courseID, grade);
	}

	public ArrayList<StudentGrade> getStudentSX(int courseID, double grade1, double grade2, String tt, String sx) {
		return gradeDal.getStudentSX(courseID, grade1, grade2, tt, sx);
	}

	public ArrayList<StudentGrade> getStudentSXByCourseId(int courseID, String tt, String sx) {
		return gradeDal.getStudentSXByCourseId(courseID, tt, sx);
	}

	public String UpdateGrade(BigDecimal grade, int studentId, int courseId) {
		if (gradeDal.UpdateGrade(grade, studentId, courseId)) {
			return "Lưu thành công";
		}
		return "Lưu thất bại";
	}

	public String addStudent(int studentid, int courseid) {
		if (gradeDal.addStudent(studentid, courseid)) {
			return "Thêm thành công";
		}
		return "Thêm thất bại";
	}

	public String delStudent(int studentid, int courseid) {
		if (gradeDal.delStudent(studentid, courseid)) {
			return "Xoá thành công";
		}
		return "Xoá thất bại";
	}
	
	public boolean checkStudent(int courseID) {
		return gradeDal.checkStudent(courseID);
	}
}
