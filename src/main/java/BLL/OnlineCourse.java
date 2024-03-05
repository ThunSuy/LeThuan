package BLL;

public class OnlineCourse {
	private int courseID;
	private String url;

	public OnlineCourse() {
	}

	public OnlineCourse(int courseID, String url) {
		this.courseID = courseID;
		this.url = url;
	}

	public int getCourseID() {
		return courseID;
	}

	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
}
