package DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BLL.OfficeAssignment;

public class OfficeAssignmentDAL {
	public ArrayList<OfficeAssignment> getAllOffice() {
		ArrayList<OfficeAssignment> arr = new ArrayList<OfficeAssignment>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from officeassignment";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					OfficeAssignment p = new OfficeAssignment();
					p.setInstructorID(rs.getInt(1));
					p.setLocation(rs.getString(2));
					p.setTimestamp(rs.getTimestamp(3));
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

	public ArrayList<OfficeAssignment> getAllOfficeSX(String tt, String sx) {
		ArrayList<OfficeAssignment> arr = new ArrayList<OfficeAssignment>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from officeassignment,person WHERE person.PersonID = officeassignment.InstructorID ORDER BY "
						+ tt + " " + sx + "";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					OfficeAssignment p = new OfficeAssignment();
					p.setInstructorID(rs.getInt(1));
					p.setLocation(rs.getString(2));
					p.setTimestamp(rs.getTimestamp(3));
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

	public ArrayList<OfficeAssignment> getAllOfficeSXByIDByLoca(String last, String first, int id, String location,
			String tt, String sx) {
		ArrayList<OfficeAssignment> arr = new ArrayList<OfficeAssignment>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from officeassignment JOIN person ON person.PersonID = officeassignment.InstructorID where lastname like '%"
						+ last + "%' or firstname like '%" + first + "%' or instructorID = " + id
						+ " or location like '%" + location + "%' ORDER BY " + tt + " " + sx + "";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					OfficeAssignment p = new OfficeAssignment();
					p.setInstructorID(rs.getInt(1));
					p.setLocation(rs.getString(2));
					p.setTimestamp(rs.getTimestamp(3));
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

	public static ArrayList<OfficeAssignment> getAllOfficeSXByTime(String timestamp, String tt, String sx) {
		ArrayList<OfficeAssignment> arr = new ArrayList<OfficeAssignment>();

		if (Connect.openConnection()) {
			try {
				String sql = "select * from officeassignment JOIN person ON person.PersonID = officeassignment.InstructorID where timestamp = ? ORDER BY "
						+ tt + " " + sx + "";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				preparedStatement.setString(1, timestamp);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					OfficeAssignment sp = new OfficeAssignment();
					sp.setInstructorID(rs.getInt("instructorID"));
					sp.setLocation(rs.getString("location"));
					sp.setTimestamp(rs.getTimestamp("timestamp"));
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

	public static ArrayList<OfficeAssignment> getOfficeSXByTimestamp(String time) {
		ArrayList<OfficeAssignment> arr = new ArrayList<OfficeAssignment>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM officeassignment WHERE timestamp = ?";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				preparedStatement.setString(1, time);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					OfficeAssignment sp = new OfficeAssignment();
					sp.setInstructorID(rs.getInt("instructorID"));
					sp.setLocation(rs.getString("location"));
					sp.setTimestamp(rs.getTimestamp("timestamp"));
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

	public static ArrayList<OfficeAssignment> getOfficeSXByIdByLoca(int id, String loca) {
		ArrayList<OfficeAssignment> arr = new ArrayList<OfficeAssignment>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM officeassignment where instructorID = " + id + " or location like '%" + loca
						+ "%'";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					OfficeAssignment sp = new OfficeAssignment();
					sp.setInstructorID(rs.getInt("instructorID"));
					sp.setLocation(rs.getString("location"));
					sp.setTimestamp(rs.getTimestamp("timestamp"));
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

	public boolean addOffice(OfficeAssignment p) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Insert into officeassignment (instructorID,location,timestamp) values(?,?,?)";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(1, p.getInstructorID());
				stmt.setString(2, p.getLocation());
				stmt.setTimestamp(3, new java.sql.Timestamp(p.getTimestamp().getTime()));
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

	public boolean editOffice(OfficeAssignment p) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Update officeassignment set location = ?, timestamp = ? where instructorID = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setInt(3, p.getInstructorID());
				stmt.setString(1, p.getLocation());
				stmt.setTimestamp(2, new java.sql.Timestamp(p.getTimestamp().getTime()));
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

	public boolean delOffice(int id) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Delete from officeassignment where InstructorID = ?";
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

	public boolean delInstructor(int id) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Delete from courseinstructor where PersonID = ?";
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
