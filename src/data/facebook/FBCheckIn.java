package data.facebook;

import data.Place;

public class FBCheckIn extends FBActivity  {

	private Place place;
	private String picture;
	private String message;

	public Place getPlace() {
		return place;
	}

	public void setPlace(Place place) {
		this.place = place;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
//
//	@Override
//	public String toString() {
//		return "FBCheckIn [place=" + place + ", picture=" + picture
//				+ ", message=" + message + ", getPlace()=" + getPlace()
//				+ ", getPicture()=" + getPicture() + ", getMessage()="
//				+ getMessage() + ", getWithWhom()=" + getWithWhom()
//				+ ", getNumberOfLikes()=" + getNumberOfLikes()
//				+ ", getNumberOfComments()=" + getNumberOfComments()
//				+ ", getLikedBy()=" + getLikedBy() + ", getCommentedBy()="
//				+ getCommentedBy() + ", getDate()=" + getDate()
//				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
//				+ ", toString()=" + super.toString() + "]";
//	}

}
