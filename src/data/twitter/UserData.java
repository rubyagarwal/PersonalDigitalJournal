package data.twitter;

public class UserData {

	private String screenName; // screen_name
	private String name;
	private String location;
	private Long noOfTweetsLiked; // favourites_count
	private Long noOfFollowers; // followers_count
	private Long noOfFriends; // friends_count

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getNoOfTweetsLiked() {
		return noOfTweetsLiked;
	}

	public void setNoOfTweetsLiked(Long noOfTweetsLiked) {
		this.noOfTweetsLiked = noOfTweetsLiked;
	}

	public Long getNoOfFollowers() {
		return noOfFollowers;
	}

	public void setNoOfFollowers(Long noOfFollowers) {
		this.noOfFollowers = noOfFollowers;
	}

	public Long getNoOfFriends() {
		return noOfFriends;
	}

	public void setNoOfFriends(Long noOfFriends) {
		this.noOfFriends = noOfFriends;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((screenName == null) ? 0 : screenName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserData other = (UserData) obj;
		if (screenName == null) {
			if (other.screenName != null)
				return false;
		} else if (!screenName.equals(other.screenName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserData [screenName=" + screenName + "]";
	}

	public String getUser() {
		return "UserData [screenName=" + screenName + ", name=" + name
				+ ", location=" + location + ", noOfTweetsLiked="
				+ noOfTweetsLiked + ", noOfFollowers=" + noOfFollowers
				+ ", noOfFriends=" + noOfFriends + "]";
	}

}
