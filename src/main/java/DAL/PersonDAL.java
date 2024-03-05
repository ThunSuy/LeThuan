package DAL;

import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import BLL.Person;


public class PersonDAL {
	
	public ArrayList<Person> getAllPerson(){
		//tạo một cái array list arr
		ArrayList<Person> arr = new ArrayList<Person>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from person";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				//duyệt từng dòng, nếu còn thì mới duyệt
				while (rs.next()) {
					Person p = new Person();
					//lưu từng giá trị trong p
					p.setPersonId(rs.getInt(1)); 
					p.setLastname(rs.getString(2));
					p.setFirstname(rs.getString(3));
					p.setHireDate(rs.getTimestamp(4));
					p.setEnrollmentDate(rs.getTimestamp(5));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach Person tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}
	public static ArrayList<Person> getAllPersonById() {
		ArrayList<Person> arr = new ArrayList<Person>();
		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM person,courseinstructor WHERE person.personID = courseinstructor.personID;";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Person sp = new Person();
					sp.setPersonId(rs.getInt("personID"));
					sp.setLastname(rs.getString("lastname"));
					sp.setFirstname(rs.getString("firstname"));
					sp.setHireDate(rs.getTimestamp("hiredate"));
					sp.setEnrollmentDate(rs.getTimestamp("enrollmentDate"));
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
	public static ArrayList<Person> getStudentByLastFirstName(String last,String first) {
	    ArrayList<Person> arr = new ArrayList<>();
	    
	    if (Connect.openConnection()) {
	        try {
	            String sql = "SELECT * FROM person WHERE lastname like '%" +last+"%' or firstname like '%"+first+"%'";
	            
	            try (PreparedStatement pstmt = Connect.con.prepareStatement(sql)) {
	                ResultSet rs = pstmt.executeQuery();

	                while (rs.next()) {
	                	Person sp = new Person();
						sp.setPersonId(rs.getInt("personID"));
						sp.setLastname(rs.getString("lastname"));
						sp.setFirstname(rs.getString("firstname"));
						sp.setHireDate(rs.getTimestamp("hiredate"));
						sp.setEnrollmentDate(rs.getTimestamp("enrollmentDate"));
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
	
	//Phương thức trả về kiểu dữ liệu danh sách array list person 
	public static ArrayList<Person> getAllPersonSX(int id,String tt,String sx,String last,String first){
		//tạo một cái array list arr
		ArrayList<Person> arr = new ArrayList<Person>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from person where PersonID = " + id + " or lastname like '%" + last + "%' or firstname like '%" + first + "%' ORDER BY "+ tt + " " + sx + "";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				//duyệt từng dòng, nếu còn thì mới duyệt
				while (rs.next()) {
					Person p = new Person();
					//lưu từng giá trị trong p
					p.setPersonId(rs.getInt(1)); 
					p.setLastname(rs.getString(2));
					p.setFirstname(rs.getString(3));
					p.setHireDate(rs.getTimestamp(4));
					p.setEnrollmentDate(rs.getTimestamp(5));
					arr.add(p);
				}
			} catch (Exception e) {
				System.out.println(e + "\nLoi lay danh sach Person tu DB");
			} finally {
				Connect.closeConnection();
			}
		}
		return arr;
	}
	public static ArrayList<Person> getAllPersonSxDate(String hire,String endate,String tt,String sx) {
		ArrayList<Person> arr = new ArrayList<Person>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM person WHERE hiredate = ? or enrollmentdate = ? ORDER BY " + tt + " " + sx + "";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				preparedStatement.setString(1, hire);
				preparedStatement.setString(2, endate);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Person p = new Person();
					p.setPersonId(rs.getInt(1)); 
					p.setLastname(rs.getString(2));
					p.setFirstname(rs.getString(3));
					p.setHireDate(rs.getTimestamp(4));
					p.setEnrollmentDate(rs.getTimestamp(5));
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
	public boolean delPerson(int id) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Delete from person where PersonID = ?";
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
	
	public boolean addPerson(Person p) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Insert into person(lastname,firstname,hiredate,enrollmentdate) values(?,?,?,?)";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				
				stmt.setString(1, p.getLastname());
				stmt.setString(2, p.getFirstname());
				stmt.setTimestamp(3, new java.sql.Timestamp(p.getHireDate().getTime()));
				stmt.setTimestamp(4, new java.sql.Timestamp(p.getEnrollmentDate().getTime()));
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
	public boolean editPerson(Person p) {
		boolean result = false;
		if (Connect.openConnection()) {
			try {
				String sql = "Update person set lastname = ?, firstname = ?,hiredate = ?, enrollmentdate =? where personid = ?";
				PreparedStatement stmt = Connect.con.prepareStatement(sql);
				stmt.setString(1, p.getLastname());
				stmt.setString(2, p.getFirstname());
				stmt.setTimestamp(3, new java.sql.Timestamp(p.getHireDate().getTime()));
				stmt.setTimestamp(4, new java.sql.Timestamp(p.getEnrollmentDate().getTime()));
				stmt.setInt(5, p.getPersonId());
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
	public ArrayList<Person> getSxByFirstName(String sx){
		ArrayList<Person> arr = new ArrayList<Person>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from person,officeassignment WHERE person.PersonID = officeassignment.InstructorID ORDER BY firstname " + sx + "";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Person p = new Person();
					p.setPersonId(rs.getInt(1)); 
					p.setLastname(rs.getString(2));
					p.setFirstname(rs.getString(3));
					p.setHireDate(rs.getTimestamp(4));
					p.setEnrollmentDate(rs.getTimestamp(5));
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
	public static ArrayList<Person> getPersonByName(int id,String last,String first) {
		ArrayList<Person> arr = new ArrayList<Person>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM person where PersonID = " + id + " or lastname like '%" + last + "%' or firstname like '%" + first + "%'";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					Person p = new Person();
					p.setPersonId(rs.getInt(1)); 
					p.setLastname(rs.getString(2));
					p.setFirstname(rs.getString(3));
					p.setHireDate(rs.getTimestamp(4));
					p.setEnrollmentDate(rs.getTimestamp(5));
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
	public static ArrayList<Person> getPersonByDate(String hire,String enda) {
		ArrayList<Person> arr = new ArrayList<Person>();

		if (Connect.openConnection()) {
			try {
				String sql = "SELECT * FROM person WHERE hiredate = ? or enrollmentdate = ?";
				PreparedStatement preparedStatement = Connect.con.prepareStatement(sql);
				preparedStatement.setString(1, hire);
				preparedStatement.setString(2, enda);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					Person p = new Person();
					p.setPersonId(rs.getInt(1)); 
					p.setLastname(rs.getString(2));
					p.setFirstname(rs.getString(3));
					p.setHireDate(rs.getTimestamp(4));
					p.setEnrollmentDate(rs.getTimestamp(5));
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
	public ArrayList<Person> getDataByFullnameOr(String last,String first){
		ArrayList<Person> arr = new ArrayList<Person>();
		if (Connect.openConnection()) {
			try {
				String sql = "select * from person where lastname like '%" + last + "%' or firstname like '%" + first + "%'";
				Statement stmt = Connect.con.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					Person p = new Person();
					p.setPersonId(rs.getInt(1)); 
					p.setLastname(rs.getString(2));
					p.setFirstname(rs.getString(3));
					p.setHireDate(rs.getTimestamp(4));
					p.setEnrollmentDate(rs.getTimestamp(5));
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
}
