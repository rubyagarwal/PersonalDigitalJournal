package data.facebook;

public class FBPost extends FBActivity {

	private String message;
	private String picture;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

//	@Override
//	public String toString() {
//		return "FBPost [message=" + message + ", getMessage()=" + getMessage()
//				+ ", getWithWhom()=" + getWithWhom() + ", getNumberOfLikes()="
//				+ getNumberOfLikes() + ", getNumberOfComments()="
//				+ getNumberOfComments() + ", getPicture()="
//				+ getPicture() + ", getLikedBy()=" + getLikedBy()
//				+ ", getCommentedBy()=" + getCommentedBy() + ", getDate()="
//				+ getDate() + ", getClass()=" + getClass() + ", hashCode()="
//				+ hashCode() + ", toString()=" + super.toString() + "]";
//	}
//
}
