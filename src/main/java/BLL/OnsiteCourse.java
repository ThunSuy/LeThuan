package BLL;

import java.sql.Time;

public class OnsiteCourse {
	private int courseID;
	private String location;
	private String days;
	private Time time;

	public OnsiteCourse(int courseID, String location, String days, Time time) {
		this.courseID = courseID;
		this.location = location;
		this.days = days;
		this.time = time;
	}

	public OnsiteCourse() {
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
