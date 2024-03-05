package BLL;

import java.sql.Timestamp;

public class Person {
	private int PersonId;
	private String Lastname;
	private String Firstname;
	private Timestamp HireDate;
	private Timestamp EnrollmentDate;	
	
	public Person(int personId, String lastname, String firstname, Timestamp hireDate, Timestamp enrollmentDate) {
		PersonId = personId;
		Lastname = lastname;
		Firstname = firstname;
		HireDate = hireDate;
		EnrollmentDate = enrollmentDate;
	}
	
	public Person() {}
	
	public int getPersonId() {
		return PersonId;
	}
	public void setPersonId(int personId) {
		PersonId = personId;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public Timestamp getHireDate() {
		return HireDate;
	}
	public void setHireDate(Timestamp hireDate) {
		HireDate = hireDate;
	}
	public Timestamp getEnrollmentDate() {
		return EnrollmentDate;
	}
	public void setEnrollmentDate(Timestamp enrollmentDate) {
		EnrollmentDate = enrollmentDate;
	}
}
