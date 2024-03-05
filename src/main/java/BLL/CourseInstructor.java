package BLL;

public class CourseInstructor {
	private int courseID;
	private int personID;
	
	public CourseInstructor(int courseID, int personID) {
		this.courseID = courseID;
		this.personID = personID;
	}

	public CourseInstructor() {
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public int getPersonID() {
		return personID;
	}

	public void setPersonID(int personID) {
		this.personID = personID;
	}
	
}
