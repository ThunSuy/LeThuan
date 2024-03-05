package DAL;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import BLL.Course;

public class CourseDAL {
	public static ArrayList<Course> getAllCourse() {
		ArrayList<Course> arr = new ArrayList<Course>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM course";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Course sp = new Course();
					sp.setCourseID(rs.getInt("courseID"));
					sp.setTitle(rs.getString("title"));
					sp.setCredits(rs.getInt("credits"));
					sp.setDepartmentID(rs.getInt("departmentID"));
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

	public static ArrayList<String> getTitle(String name) {
		ArrayList<String> arr = new ArrayList<String>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT title FROM course,department Where course.departmentID = department.departmentID AND name = '"
						+ name + "'";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String tensp = rs.getString("title");
					arr.add(tensp);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public static ArrayList<Course> getAllCourseOnline() {
		ArrayList<Course> arr = new ArrayList<Course>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM course,onlinecourse WHERE course.courseID = onlinecourse.courseID";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Course sp = new Course();
					sp.setCourseID(rs.getInt("courseID"));
					sp.setTitle(rs.getString("title"));
					sp.setCredits(rs.getInt("credits"));
					sp.setDepartmentID(rs.getInt("departmentID"));
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

	public static ArrayList<Course> getAllCourseOnsite() {
		ArrayList<Course> arr = new ArrayList<Course>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM course,onsitecourse WHERE course.courseID = onsitecourse.courseID";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Course sp = new Course();
					sp.setCourseID(rs.getInt("courseID"));
					sp.setTitle(rs.getString("title"));
					sp.setCredits(rs.getInt("credits"));
					sp.setDepartmentID(rs.getInt("departmentID"));
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

	public static int getCourseIDByTitleName(String name, String title) {
		int courseID = 0;
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT course.CourseID FROM course,department WHERE course.Title = '" + title
						+ "' AND department.Name = '" + name + "'";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					courseID = rs.getInt("courseID");
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return courseID;
	}

	public static ArrayList<Integer> getCbbCourse() {
		// tạo một cái array list arr
		ArrayList<Integer> arr = new ArrayList<Integer>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT CourseID FROM  courseinstructor";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					arr.add(rs.getInt("courseID"));
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach Person tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public ArrayList<Course> findCourseOnline(String condition) {
		ArrayList<Course> arr = new ArrayList<Course>();
		if (Connect.openConnection()) {
			try {
				String sql;
				Statement statement = Connect.con.createStatement();
				if (isNumeric(condition)) {
					sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.url " + "FROM Course c "
							+ "INNER JOIN OnlineCourse o ON c.courseID = o.courseID " + "WHERE c.courseID = "
							+ condition + " OR c.credits = " + condition + " OR c.departmentID = " + condition;

				} else {
					sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.url " + "FROM Course c "
							+ "INNER JOIN OnlineCourse o ON c.courseID = o.courseID " + "WHERE c.title LIKE '%"
							+ condition + "%' OR o.url LIKE '%" + condition + "%'";

				}

				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					Course p = new Course();
					p.setCourseID(rs.getInt(1));
					p.setTitle(rs.getString(2));
					p.setCredits(rs.getInt(3));
					p.setDepartmentID(rs.getInt(4));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nfindCourseOnline");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public ArrayList<Course> findCombineSortOnline(String conditionSort, String conditionFind, boolean isAsc) {
		ArrayList<Course> arr = new ArrayList<Course>();
		if (Connect.openConnection()) {
			try {
				String sql;
				Statement statement = Connect.con.createStatement();
				if (isNumeric(conditionFind)) {
					sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.url " + "FROM Course c "
							+ "INNER JOIN OnlineCourse o ON c.courseID = o.courseID " + "WHERE c.courseID = "
							+ conditionFind + " OR c.credits = " + conditionFind + " OR c.departmentID = "
							+ conditionFind + " order by " + conditionSort;

				} else {
					sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.url " + "FROM Course c "
							+ "INNER JOIN OnlineCourse o ON c.courseID = o.courseID " + "WHERE c.title LIKE '%"
							+ conditionFind + "%' OR o.url LIKE '%" + conditionFind + "%'" + " order by "
							+ conditionSort;

				}
				if (!isAsc) {
					sql = sql + " DESC";
				}
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					Course p = new Course();
					p.setCourseID(rs.getInt(1));
					p.setTitle(rs.getString(2));
					p.setCredits(rs.getInt(3));
					p.setDepartmentID(rs.getInt(4));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nfindCombineSort");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public ArrayList<Course> findCombineSortOnsite(String conditionSort, String conditionFind, boolean isAsc) {
		ArrayList<Course> arr = new ArrayList<Course>();
		if (Connect.openConnection()) {
			try {
				String sql;
				Statement statement = Connect.con.createStatement();
				sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.location, o.days, o.time "
						+ "FROM Course c " + "INNER JOIN OnsiteCourse o ON c.courseID = o.courseID "
						+ "WHERE c.title LIKE '%" + conditionFind + "%' OR o.location LIKE '%" + conditionFind
						+ "%' OR o.days LIKE '%" + conditionFind + "%'" + " order by " + conditionSort;

				if (isNumeric(conditionFind)) {
					sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.location, o.days, o.time "
							+ "FROM Course c " + "INNER JOIN OnsiteCourse o ON c.courseID = o.courseID "
							+ "WHERE c.courseID = " + conditionFind + " OR c.credits = " + conditionFind
							+ " OR c.departmentID = " + conditionFind + " order by " + conditionSort;
				}
				if (isValidTime(conditionFind)) {
					sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.location, o.days, o.time "
							+ "FROM Course c " + "INNER JOIN OnsiteCourse o ON c.courseID = o.courseID "
							+ "WHERE TIME(Time) = STR_TO_DATE('" + conditionFind + "', '%H:%i:%s')" + " order by "
							+ conditionSort;
				}
				if (!isAsc) {
					sql = sql + " DESC";
				}
				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					Course p = new Course();
					p.setCourseID(rs.getInt(1));
					p.setTitle(rs.getString(2));
					p.setCredits(rs.getInt(3));
					p.setDepartmentID(rs.getInt(4));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nfindCombineSort");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public int newCourseID() {
		int courseID = -1;
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM `course` ORDER BY CourseID DESC LIMIT 1";
				Statement statement = Connect.con.createStatement();
				ResultSet rs = statement.executeQuery(sql);
				if (rs.next()) {
					courseID = rs.getInt(1);
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return courseID;
	}

	public ArrayList<Course> findCourseOnsite(String condition) {
		ArrayList<Course> arr = new ArrayList<Course>();
		if (Connect.openConnection()) {
			try {
				String sql;
				Statement statement = Connect.con.createStatement();
				sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.location, o.days, o.time "
						+ "FROM Course c " + "INNER JOIN OnsiteCourse o ON c.courseID = o.courseID "
						+ "WHERE c.title LIKE '%" + condition + "%' OR o.location LIKE '%" + condition
						+ "%' OR o.days LIKE '%" + condition + "%'";
				if (isNumeric(condition)) {
					sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.location, o.days, o.time "
							+ "FROM Course c " + "INNER JOIN OnsiteCourse o ON c.courseID = o.courseID "
							+ "WHERE c.courseID = " + condition + " OR c.credits = " + condition
							+ " OR c.departmentID = " + condition;

				}
				if (isValidTime(condition)) {
					sql = "SELECT c.courseID, c.title, c.credits, c.departmentID, o.location, o.days, o.time "
							+ "FROM Course c " + "INNER JOIN OnsiteCourse o ON c.courseID = o.courseID "
							+ "WHERE TIME(Time) = STR_TO_DATE('" + condition + "', '%H:%i:%s');";
				}

				ResultSet rs = statement.executeQuery(sql);
				while (rs.next()) {
					Course p = new Course();
					p.setCourseID(rs.getInt(1));
					p.setTitle(rs.getString(2));
					p.setCredits(rs.getInt(3));
					p.setDepartmentID(rs.getInt(4));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nfindCourseOnsite");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;

	}

	public boolean addCourse(Course c) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Insert into course(title, credits, departmentid) values(?,?,?)";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setString(1, c.getTitle());
				stmt.setInt(2, c.getCredits());
				stmt.setInt(3, c.getDepartmentID());
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

	public boolean editCourse(int courseid, String title, int credits, int departmentid) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Update course set title = ?, credits = ?,departmentID = ? where courseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(4, courseid);
				stmt.setString(1, title);
				stmt.setInt(2, credits);
				stmt.setInt(3, departmentid);
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

	public boolean delCourse(int courseid) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Delete from course where CourseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, courseid);
				int rowsAffected = stmt.executeUpdate();
				if (rowsAffected == 1) {
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

	public boolean isNumeric(String str) {
		if (str == null || str.isEmpty()) {
			return false;
		}
		return str.matches("-?\\d+(\\.\\d+)?");
	}

	public static boolean isValidTime(String input) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		sdf.setLenient(false);

		try {
			sdf.parse(input);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
}
