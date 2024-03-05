package DAL;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import BLL.StudentGrade;

public class StudentGradeDAL {
	public static ArrayList<StudentGrade> getStudentIdByCourseID(int courseID) {
		ArrayList<StudentGrade> arr = new ArrayList<StudentGrade>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM studentgrade WHERE CourseID = " + courseID;
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					StudentGrade sp = new StudentGrade();
					sp.setEnrollmentID(rs.getInt("enrollmentID"));
					sp.setStudentID(rs.getInt("studentID"));
					sp.setCourseID(rs.getInt("courseID"));
					sp.setGrade(rs.getBigDecimal("grade"));
					arr.add(sp);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public static ArrayList<StudentGrade> getStudentByGrade(int courseID, double grade1, double grade2) {
		ArrayList<StudentGrade> arr = new ArrayList<>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM studentgrade WHERE CourseID = ? AND Grade >= ? AND Grade <= ?";

				try (PreparedStatement pstmt = Connect.con.prepareStatement(sql)) {
					pstmt.setInt(1, courseID);
					pstmt.setDouble(2, grade1);
					pstmt.setDouble(3, grade2);

					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						StudentGrade sp = new StudentGrade();
						sp.setEnrollmentID(rs.getInt("enrollmentID"));
						sp.setStudentID(rs.getInt("studentID"));
						sp.setCourseID(rs.getInt("courseID"));
						sp.setGrade(rs.getBigDecimal("grade"));
						arr.add(sp);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public static ArrayList<StudentGrade> getStudentSX(int courseID, double grade1, double grade2, String tt,
			String sx) {
		ArrayList<StudentGrade> arr = new ArrayList<>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM studentgrade,person WHERE studentgrade.StudentID=person.PersonID AND CourseID = ? AND Grade >= ? AND Grade <= ? ORDER BY "
						+ tt + " " + sx + "";

				try (PreparedStatement pstmt = Connect.con.prepareStatement(sql)) {
					pstmt.setInt(1, courseID);
					pstmt.setDouble(2, grade1);
					pstmt.setDouble(3, grade2);

					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						StudentGrade sp = new StudentGrade();
						sp.setEnrollmentID(rs.getInt("enrollmentID"));
						sp.setStudentID(rs.getInt("studentID"));
						sp.setCourseID(rs.getInt("courseID"));
						sp.setGrade(rs.getBigDecimal("grade"));
						arr.add(sp);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public static ArrayList<StudentGrade> getStudentSXByCourseId(int courseID, String tt, String sx) {
		ArrayList<StudentGrade> arr = new ArrayList<>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM studentgrade,person WHERE studentgrade.StudentID=person.PersonID AND CourseID = ? ORDER BY "
						+ tt + " " + sx + "";

				try (PreparedStatement pstmt = Connect.con.prepareStatement(sql)) {
					pstmt.setInt(1, courseID);

					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						StudentGrade sp = new StudentGrade();
						sp.setEnrollmentID(rs.getInt("enrollmentID"));
						sp.setStudentID(rs.getInt("studentID"));
						sp.setCourseID(rs.getInt("courseID"));
						sp.setGrade(rs.getBigDecimal("grade"));
						arr.add(sp);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public static ArrayList<StudentGrade> getStudentGradeIsnull(int courseID) {
		ArrayList<StudentGrade> arr = new ArrayList<>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM studentgrade,person WHERE studentgrade.StudentID=person.PersonID and studentgrade.grade IS NULL AND CourseID = ? ";

				try (PreparedStatement pstmt = Connect.con.prepareStatement(sql)) {
					pstmt.setInt(1, courseID);
					ResultSet rs = pstmt.executeQuery();
					while (rs.next()) {
						StudentGrade sp = new StudentGrade();
						sp.setEnrollmentID(rs.getInt("enrollmentID"));
						sp.setStudentID(rs.getInt("studentID"));
						sp.setCourseID(rs.getInt("courseID"));
						sp.setGrade(rs.getBigDecimal("grade"));
						arr.add(sp);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public boolean UpdateGrade(BigDecimal grade, int studentId, int courseId) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Update studentgrade set grade = ? where studentID = ? and courseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setBigDecimal(1, grade);
				stmt.setInt(2, studentId);
				stmt.setInt(3, courseId);
				int check = stmt.executeUpdate();
				if (check > 0) {
					result = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return result;
	}

	public boolean addStudent(int studenid, int courseid) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Insert into studentgrade (studentID,courseID) values(?,?)";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, studenid);
				stmt.setInt(2, courseid);
				if (stmt.executeUpdate() >= 1) {
					result = true;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return result;
	}

	public boolean delStudent(int studentid, int courseid) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Delete from studentgrade where studentID = ? and courseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, studentid);
				stmt.setInt(2, courseid);
				int rowsAffected = stmt.executeUpdate();
				if (rowsAffected == 1) {
					result = true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}

		}
		return result;
	}

	public static ArrayList<StudentGrade> getFindByGrade(int courseID, double grade) {
		ArrayList<StudentGrade> arr = new ArrayList<>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM studentgrade,person WHERE studentgrade.StudentID=person.PersonID AND CourseID = ? AND Grade = ?";

				try (PreparedStatement pstmt = Connect.con.prepareStatement(sql)) {
					pstmt.setInt(1, courseID);
					pstmt.setDouble(2, grade);

					ResultSet rs = pstmt.executeQuery();

					while (rs.next()) {
						StudentGrade sp = new StudentGrade();
						sp.setEnrollmentID(rs.getInt("enrollmentID"));
						sp.setStudentID(rs.getInt("studentID"));
						sp.setCourseID(rs.getInt("courseID"));
						sp.setGrade(rs.getBigDecimal("grade"));
						arr.add(sp);
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}
	
	public boolean checkStudent(int courseID) {
		boolean exists = false;
		String sql = "SELECT EXISTS (SELECT 1 FROM StudentGrade ci WHERE ci.courseID = ?)";
		if (Connect.openConnection()) {
			try {
				PreparedStatement pstmt = Connect.con.prepareStatement(sql);
				pstmt.setInt(1, courseID);
				try (ResultSet rs = pstmt.executeQuery()) {
					if (rs.next()) {
						exists = rs.getBoolean(1);
					}
				}

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return exists;
	}
}
