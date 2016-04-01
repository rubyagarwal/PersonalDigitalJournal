package data.facebook;

public class Work {

	private String position;
	private String location;
	private String employer;
	private String startDate;
	private String endDate;
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Work [position=" + position + ", location=" + location
				+ ", employer=" + employer + ", startDate=" + startDate
				+ ", endDate=" + endDate + "]";
	}
	
}
