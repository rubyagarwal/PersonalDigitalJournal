package data.foursquare;

import java.util.List;
import java.util.Set;

import data.Place;

public class FSData {

	private String message;
//	private Date date;
	private Set<String> withTag;
	private Long numberOfLikes;
	private Long numberOfComments;
	private List<String> commentedBy;
	private Set<String> likedBy;
	private Place place;
	private Integer date;
	private Integer month;
	private Integer year;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<String> getWithTag() {
		return withTag;
	}

	public void setWithTag(Set<String> withTag) {
		this.withTag = withTag;
	}

	public Long getNumberOfLikes() {
		return numberOfLikes;
	}

	public void setNumberOfLikes(Long numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	
	public List<String> getCommentedBy() {
		return commentedBy;
	}

	public void setCommentedBy(List<String> commentedBy) {
		this.commentedBy = commentedBy;
	}

	public Set<String> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(Set<String> likedBy) {
		this.likedBy = likedBy;
	}

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Long getNumberOfComments() {
		return numberOfComments;
	}

	public void setNumberOfComments(Long numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	@Override
	public String toString() {
		return "FSData [message=" + message + ", withTag=" + withTag
				+ ", numberOfLikes=" + numberOfLikes + ", numberOfComments="
				+ numberOfComments + ", commentedBy=" + commentedBy
				+ ", likedBy=" + likedBy + ", place=" + place + ", date="
				+ date + ", month=" + month + ", year=" + year + "]";
	}

	
}
