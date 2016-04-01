package data;

import java.util.List;

public class Statistics {

	private Long totalActivities;
	private Long totalTweets;
	private Long totalPosts;
	private Long totalCheckins;

	// private Long totalLikes;
	// private Long totalComments;
	//
	// private Long totalFbLikes;
	// private Long totalFbComments;
	// private Long totalFsLikes;
	// private Long totalFsComments;
	// private Long totalTwLikes;
	// private Long totalTwComments;
	//
	// private Long maxLikes;
	// private Long maxComments;

	private String withWhomMax;
	private Integer withWhomMaxCount;
	
	private String topLikers;
	private String topCommenters;

	public Long getTotalActivities() {
		return totalActivities;
	}

	public Long getTotalTweets() {
		return totalTweets;
	}

	public Long getTotalPosts() {
		return totalPosts;
	}

	public Long getTotalCheckins() {
		return totalCheckins;
	}


	public void setTotalActivities(Long totalActivities) {
		this.totalActivities = totalActivities;
	}

	public void setTotalTweets(Long totalTweets) {
		this.totalTweets = totalTweets;
	}

	public void setTotalPosts(Long totalPosts) {
		this.totalPosts = totalPosts;
	}

	public void setTotalCheckins(Long totalCheckins) {
		this.totalCheckins = totalCheckins;
	}

	
	public String getWithWhomMax() {
		return withWhomMax;
	}

	public Integer getWithWhomMaxCount() {
		return withWhomMaxCount;
	}

	public void setWithWhomMax(String withWhomMax) {
		this.withWhomMax = withWhomMax;
	}

	public void setWithWhomMaxCount(Integer withWhomMaxCount) {
		this.withWhomMaxCount = withWhomMaxCount;
	}


	public String getTopLikers() {
		return topLikers;
	}

	public void setTopLikers(String topLikers) {
		this.topLikers = topLikers;
	}

	@Override
	public String toString() {
		return "Statistics [totalActivities=" + totalActivities
				+ ", totalTweets=" + totalTweets + ", totalPosts=" + totalPosts
				+ ", totalCheckins=" + totalCheckins + ", withWhomMax="
				+ withWhomMax + ", withWhomMaxCount=" + withWhomMaxCount
				+ ", topLikers=" + topLikers + "]";
	}

	public String getTopCommenters() {
		return topCommenters;
	}

	public void setTopCommenters(String topCommenters) {
		this.topCommenters = topCommenters;
	}

	



}
