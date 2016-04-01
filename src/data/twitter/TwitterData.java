package data.twitter;

import java.util.Set;

public class TwitterData {

	private String message;
	private Set<String> hashtag;
	//private Date date;
	private Long favoriteCount; // per tweet favorite_count
	private Long retweetCount;
	private Boolean yourTweet; // 0 - You were mentioned in the tweet; 1- You
								// tweeted
	private Integer date;
	private Integer month;
	private Integer year;
	
	public Boolean getYourTweet() {
		return yourTweet;
	}

	public void setYourTweet(Boolean yourTweet) {
		this.yourTweet = yourTweet;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Set<String> getHashtag() {
		return hashtag;
	}

	public void setHashtag(Set<String> hashtag) {
		this.hashtag = hashtag;
	}

	public Long getFavoriteCount() {
		return favoriteCount;
	}

	public void setFavoriteCount(Long favoriteCount) {
		this.favoriteCount = favoriteCount;
	}

	public Long getRetweetCount() {
		return retweetCount;
	}

	public void setRetweetCount(Long retweetCount) {
		this.retweetCount = retweetCount;
	}

	public Boolean getyourTweet() {
		return yourTweet;
	}

	public void setyourTweet(Boolean yourTweet) {
		this.yourTweet = yourTweet;
	}

	@Override
	public String toString() {
		return "TwitterData [message=" + message + ", hashtag=" + hashtag
				+ ", date=" + date + ", favoriteCount=" + favoriteCount
				+ ", retweetCount=" + retweetCount + ", yourTweet=" + yourTweet
				+ "]";
	}

}
