package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import BLL.OnsiteCourse;

public class OnsiteCourseDAL {
	public ArrayList<OnsiteCourse> getAllOnsiteCourse() {
		ArrayList<OnsiteCourse> arr = new ArrayList<OnsiteCourse>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from onsitecourse ";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					OnsiteCourse p = new OnsiteCourse();
					p.setCourseID(rs.getInt(1));
					p.setLocation(rs.getString(2));
					p.setDays(rs.getString(3));
					p.setTime(rs.getTime(4));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach OnsiteCourse tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public ArrayList<OnsiteCourse> sortByConditon(String condition, boolean isAsc) {
		ArrayList<OnsiteCourse> arr = new ArrayList<OnsiteCourse>();
		if (Connect.openConnection()) {
			try {
				String sql;
				if (isAsc) {
					sql = "select * from onsitecourse order by " + condition;
				} else {
					sql = "select * from onsitecourse order by " + condition + " DESC";
				}
				System.out.println(sql);
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					OnsiteCourse p = new OnsiteCourse();
					p.setCourseID(rs.getInt(1));
					p.setLocation(rs.getString(2));
					p.setDays(rs.getString(3));
					p.setTime(rs.getTime(4));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach OnsiteCourse tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}

	public boolean addOnsiteCourse(OnsiteCourse c) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Insert into onsitecourse values(?,?,?,?)";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, c.getCourseID());
				stmt.setString(2, c.getLocation());
				stmt.setString(3, c.getDays());
				stmt.setTime(4, c.getTime());
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

	public boolean editOnsiteCourse(int courseID, String location, String days, Time time) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Update onsitecourse set location = ?, days = ?, time =? where courseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(4, courseID);
				stmt.setString(1, location);
				stmt.setString(2, days);
				stmt.setTime(3, time);
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

	public boolean delOnsiteCourse(int id) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Delete from onsitecourse where CourseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, id);
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
}
