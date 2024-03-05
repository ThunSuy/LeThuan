package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BLL.OnlineCourse;

public class OnlineCourseDAL {
	
	public ArrayList<OnlineCourse> getAllOnlineCourse(){
		ArrayList<OnlineCourse> arr = new ArrayList<OnlineCourse>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from onlinecourse";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					OnlineCourse p = new OnlineCourse();
					p.setCourseID(rs.getInt(1));
					p.setUrl(rs.getString(2));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach OnlineCourse tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}
	
	public ArrayList<OnlineCourse> sortByCondition(String condition, boolean isAsc){
		ArrayList<OnlineCourse> arr = new ArrayList<OnlineCourse>();
		if (Connect.openConnection()) {
			try {
				String sql;
				if (isAsc) {
					sql = "select * from onlinecourse order by "+ condition;
				} else {
					sql = "select * from onlinecourse order by "+ condition + " DESC";
				}
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					OnlineCourse p = new OnlineCourse();
					p.setCourseID(rs.getInt(1));
					p.setUrl(rs.getString(2));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi sortByCondition");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}
	
	public ArrayList<OnlineCourse> findOnlineCourse(String condition){
		ArrayList<OnlineCourse> arr = new ArrayList<OnlineCourse>();
		if (Connect.openConnection()) {
			try {
				String sql;
				int temp;
				try {
					temp = Integer.parseInt(condition);
					sql = "select * from onlinecourse where CourseID ="+condition;
				} catch (Exception e) {
					sql ="select * from onlinecourse where url LIKE N'%"+condition+"%'";
				}
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					OnlineCourse p = new OnlineCourse();
					p.setCourseID(rs.getInt(1));
					p.setUrl(rs.getString(2));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi findOnlineCourse");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}
	
	public boolean addOnlineCourse(OnlineCourse c) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Insert into onlinecourse values(?,?)";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, c.getCourseID());
				stmt.setString(2, c.getUrl());
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

	public boolean editOnlineCourse(int courseID, String url) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Update onlinecourse set url = ? where courseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(2, courseID);
				stmt.setString(1, url);
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
	
	public boolean delOnlineCourse(int id) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Delete from onlinecourse where CourseID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, id);
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
}
