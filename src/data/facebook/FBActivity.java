package data.facebook;

import java.util.Set;

public class FBActivity {

	//private Date date;
	private Set<String> withWhom;
	private Long numberOfLikes;
	private Long numberOfComments;
	private Set<String> likedBy;
	private Set<String> commentedBy;
	private Integer date;
	private Integer month;
	private Integer year;

	public Set<String> getWithWhom() {
		return withWhom;
	}

	public Long getNumberOfLikes() {
		return numberOfLikes;
	}

	public Long getNumberOfComments() {
		return numberOfComments;
	}

	public Set<String> getLikedBy() {
		return likedBy;
	}

	public Set<String> getCommentedBy() {
		return commentedBy;
	}

	public void setWithWhom(Set<String> withWhom) {
		this.withWhom = withWhom;
	}

	public void setNumberOfLikes(Long numberOfLikes) {
		this.numberOfLikes = numberOfLikes;
	}

	public void setNumberOfComments(Long numberOfComments) {
		this.numberOfComments = numberOfComments;
	}

	public void setLikedBy(Set<String> likedBy) {
		this.likedBy = likedBy;
	}

	public void setCommentedBy(Set<String> commentedBy) {
		this.commentedBy = commentedBy;
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
}
