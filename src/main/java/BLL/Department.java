package BLL;

import java.sql.Timestamp;

public class Department {
	private int departmentID;
	private String name;
	private double budget;
	private Timestamp startDate;
	private int administrator;
	
	public Department(int departmentID, String name, double budget, Timestamp startDate, int administrator) {
		this.departmentID = departmentID;
		this.name = name;
		this.budget = budget;
		this.startDate = startDate;
		this.administrator = administrator;
	}

	public Department() {
	}

	public int getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(int departmentID) {
		this.departmentID = departmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double budget) {
		this.budget = budget;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public int getAdministrator() {
		return administrator;
	}

	public void setAdministrator(int administrator) {
		this.administrator = administrator;
	}
}
