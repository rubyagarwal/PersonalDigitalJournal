package data.facebook;

import java.util.List;

public class UserProfile {

	private String bio;
	private String name;
	private String hometown;
	private List<Work> work;
	private String bday;
	private String email;
	private String location;
	private List<Education> edu;
	private String gender;
	
	public String getBio() {
		return bio;
	}
	public void setBio(String bio) {
		this.bio = bio;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHometown() {
		return hometown;
	}
	public void setHometown(String hometown) {
		this.hometown = hometown;
	}
	public List<Work> getWork() {
		return work;
	}
	public void setWork(List<Work> work) {
		this.work = work;
	}
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<Education> getEdu() {
		return edu;
	}
	public void setEdu(List<Education> edu) {
		this.edu = edu;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public String toString() {
		return "UserProfile [bio=" + bio + ", name=" + name + ", hometown="
				+ hometown + ", work=" + work + ", bday=" + bday + ", email="
				+ email + ", location=" + location + ", edu=" + edu + "]";
	}
	
	
}
