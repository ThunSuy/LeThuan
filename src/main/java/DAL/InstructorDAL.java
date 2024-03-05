package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BLL.CourseInstructor;

public class InstructorDAL {
	public ArrayList<CourseInstructor> getAllCourseInstructor() {
		// tạo một cái array list arr
		ArrayList<CourseInstructor> arr = new ArrayList<CourseInstructor>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from courseinstructor";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				// duyệt từng dòng, nếu còn thì mới duyệt
				while (rs.next()) {
					CourseInstructor p = new CourseInstructor();
					// lưu từng giá trị trong p
					p.setCourseID(rs.getInt(1));
					p.setPersonID(rs.getInt(2));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach Course tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public static ArrayList<CourseInstructor> findByTitle(String title) {
		ArrayList<CourseInstructor> arr = new ArrayList<CourseInstructor>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM course JOIN courseinstructor on course.CourseID = courseinstructor.CourseID WHERE course.Title like '%"
						+ title + "%'";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					CourseInstructor p = new CourseInstructor();
					p.setCourseID(rs.getInt("CourseID"));
					p.setPersonID(rs.getInt("PersonID"));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public boolean addInstructor(CourseInstructor p) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Insert into courseinstructor(PersonID,CourseID) values(?,?)";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, p.getPersonID());
				stmt.setInt(2, p.getCourseID());
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

	public boolean editInstructor(CourseInstructor p) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Update courseinstructor set PersonID = ? where CourseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, p.getPersonID());
				stmt.setInt(2, p.getCourseID());
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

	public boolean checkCourseInstructor(int courseID) {
		boolean exists = false;
		String sql = "SELECT EXISTS (SELECT 1 FROM CourseInstructor ci WHERE ci.courseID = ?)";
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
