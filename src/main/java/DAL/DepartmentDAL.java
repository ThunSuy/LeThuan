package DAL;

import java.sql.Date;
import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import BLL.Course;
import BLL.Department;


public class DepartmentDAL {
	public static ArrayList<String> getAllName() {
		ArrayList<String> arr = new ArrayList<String>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT name FROM department";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					String tensp = rs.getString("name");
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
	public static ArrayList<Department> getAllDepartment() {
		ArrayList<Department> arr = new ArrayList<Department>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM department";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Department sp = new Department();
					sp.setDepartmentID(rs.getInt("departmentID"));
					sp.setName(rs.getString("name"));
					sp.setBudget(rs.getInt("budget"));
					sp.setStartDate(rs.getTimestamp("startdate"));
					sp.setAdministrator(rs.getInt("administrator"));
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
	public static ArrayList<Department> getDeparByStart(String startdate) {
		ArrayList<Department> arr = new ArrayList<Department>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM department WHERE startdate = ?";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				preparedStatement.setString(1, startdate);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Department sp = new Department();
					sp.setDepartmentID(rs.getInt("departmentID"));
					sp.setName(rs.getString("name"));
					sp.setBudget(rs.getInt("budget"));
					sp.setStartDate(rs.getTimestamp("startdate"));
					sp.setAdministrator(rs.getInt("administrator"));
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
	public static ArrayList<Department> getDeparByBudget(int id,double budget) {
		ArrayList<Department> arr = new ArrayList<Department>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM department where departmentID = " + id + " or budget = " + budget;
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Department sp = new Department();
					sp.setDepartmentID(rs.getInt("departmentID"));
					sp.setName(rs.getString("name"));
					sp.setBudget(rs.getInt("budget"));
					sp.setStartDate(rs.getTimestamp("startdate"));
					sp.setAdministrator(rs.getInt("administrator"));
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
	public static ArrayList<Department> getDeparByName(String name) {
		ArrayList<Department> arr = new ArrayList<Department>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM department where name like '%" + name + "%'";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Department sp = new Department();
					sp.setDepartmentID(rs.getInt("departmentID"));
					sp.setName(rs.getString("name"));
					sp.setBudget(rs.getInt("budget"));
					sp.setStartDate(rs.getTimestamp("startdate"));
					sp.setAdministrator(rs.getInt("administrator"));
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
	public static ArrayList<Department> getDeparSx(int id,String name,double buget,String tt,String sx) {
		ArrayList<Department> arr = new ArrayList<Department>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM department where departmentID = " + id + " or budget = " + buget + " or name like '%" + name + "%' ORDER BY " + tt + " " + sx + "";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Department sp = new Department();
					sp.setDepartmentID(rs.getInt("departmentID"));
					sp.setName(rs.getString("name"));
					sp.setBudget(rs.getInt("budget"));
					sp.setStartDate(rs.getTimestamp("startdate"));
					sp.setAdministrator(rs.getInt("administrator"));
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
	public static ArrayList<Department> getDeparSxByStart(String startdate,String tt,String sx) {
		ArrayList<Department> arr = new ArrayList<Department>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM department WHERE startdate = ? ORDER BY " + tt + " " + sx + "";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				preparedStatement.setString(1, startdate);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Department sp = new Department();
					sp.setDepartmentID(rs.getInt("departmentID"));
					sp.setName(rs.getString("name"));
					sp.setBudget(rs.getInt("budget"));
					sp.setStartDate(rs.getTimestamp("startdate"));
					sp.setAdministrator(rs.getInt("administrator"));
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
	public static ArrayList<String> getDeparByNameByTitle(String name,String title) {
		ArrayList<String> arr = new ArrayList<String>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM course,department where name = '" + name + "' AND title = '" + title + "'";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					arr.add(rs.getString("title"));
					arr.add(rs.getString("credits"));
					arr.add(rs.getString("name"));
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}
	public static int getDeparId() {
		int id = 0;

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM department\r\n"
						+ "ORDER BY DepartmentID DESC LIMIT 1";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					id = Integer.parseInt(rs.getString("departmentID"));
				}
			} catch (Exception e) {
				System.out.println(e);
			} finally {
				Connect.closeConnection();
			}
		}
		return id;
	}
	public boolean addDepartment(Department p) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Insert into department (departmentID,name,budget,startdate,administrator) values(?,?,?,?,?)";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, p.getDepartmentID());
				stmt.setString(2, p.getName());
				stmt.setDouble(3, p.getBudget());
				stmt.setTimestamp(4, new java.sql.Timestamp(p.getStartDate().getTime()));
				stmt.setInt(5, p.getAdministrator());
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
	public boolean editDepartment(Department p) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Update department set name = ?, budget = ?,startdate = ?, administrator =? where departmentID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(5, p.getDepartmentID());
				stmt.setString(1, p.getName());
				stmt.setDouble(2, p.getBudget());
				stmt.setTimestamp(3, new java.sql.Timestamp(p.getStartDate().getTime()));
				stmt.setInt(4, p.getAdministrator());
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
	public boolean delDepartment(int id) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Delete from department where departmentID = ?";
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
	public static boolean getById(int id) {
		boolean result = false;

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM course where departmentID = " + id;
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
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
