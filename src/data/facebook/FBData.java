package data.facebook;

import java.util.List;

public class FBData {

	private List<FBCheckIn> checkIn;
	private List<FBPost> post;

	public List<FBCheckIn> getCheckIn() {
		return checkIn;
	}

	public List<FBPost> getPost() {
		return post;
	}

	public void setCheckIn(List<FBCheckIn> checkIn) {
		this.checkIn = checkIn;
	}

	public void setPost(List<FBPost> post) {
		this.post = post;
	}

//	@Override
//	public String toString() {
//		return "FBData [checkIn=" + checkIn + ", post=" + post + "]";
//	}

}
