package DAL;

import java.util.ArrayList;

import BLL.Course;
import BLL.StudentGrade;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class ChartDAL {

	public static int getNumStudent() {
		int num = 0;
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM studentgrade";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					num++;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return num;
	}

	public int getNumInstructor() {
		int num = 0;
		if (Connect.openConnection()) {
			try {
				String sql = "select * from officeassignment";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					num++;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return num;
	}

	public int getNumCourse() {
		int num = 0;
		if (Connect.openConnection()) {
			try {
				String sql = "select * from course";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					num++;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return num;
	}

	public int getNumOnlineCourse() {
		int num = 0;
		if (Connect.openConnection()) {
			try {
				String sql = "select * from onlinecourse";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					num++;
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach OnlineCourse tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return num;
	}

	public int getNumOnsiteCourse() {
		int num = 0;
		if (Connect.openConnection()) {
			try {
				String sql = "select * from onsitecourse ";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					num++;
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach OnsiteCourse tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return num;
	}

	public int getNumCourseInstructor() {
		int num = 0;
		if (Connect.openConnection()) {
			try {
				String sql = "select * from courseinstructor";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					num++;
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return num;
	}

	public int getUnassignedInstructorsCount() {
		int unassignedCount = 0;
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT COUNT(*) FROM officeassignment WHERE InstructorID NOT IN (SELECT DISTINCT PersonID FROM CourseInstructor)";
				PreparedStatement statement = Connect.con.prepareStatement(sql);
				ResultSet resultSet = statement.executeQuery();

				if (resultSet.next()) {
					unassignedCount = resultSet.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}

		return unassignedCount;
	}

	public ArrayList<Integer> getUniqueCourseIDs() {
		ArrayList<Integer> courseIDs = new ArrayList<>();
		String query = "SELECT DISTINCT courseID FROM studentgrade";

		if (Connect.openConnection()) {
			try {
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				while (rs.next()) {
					int courseID = rs.getInt("courseID");
					courseIDs.add(courseID);
				}

			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}

		return courseIDs;

	}

	public ArrayList<Course> getAllCourse() {
		ArrayList<Course> arr = new ArrayList<Course>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from course";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Course p = new Course();
					p.setCourseID(rs.getInt(1));
					p.setTitle(rs.getString(2));
					p.setCredits(rs.getInt(3));
					p.setDepartmentID(rs.getInt(4));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach course tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public ArrayList<StudentGrade> getStudentIdByCourseID(int courseID) {
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

}
