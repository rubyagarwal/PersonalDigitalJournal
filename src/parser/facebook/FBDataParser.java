package parser.facebook;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import data.Place;
import data.facebook.FBCheckIn;
import data.facebook.FBData;
import data.facebook.FBPost;

public class FBDataParser {

	@SuppressWarnings("unchecked")
	public FBData extractRelevantFbData(DBCollection coll, String user)
			throws Exception {
		// read json from collection
		// extract data populate pojos
		FBData fData = new FBData();
		if (coll != null) {
			System.out
					.println("Collection not null. Now read and extract data.");

			DBCursor cursor = coll.find();
			System.out.println("Found number of records: " + cursor.size());
			List<FBCheckIn> checkIn = new ArrayList<FBCheckIn>();
			List<FBPost> post = new ArrayList<FBPost>();
			while (cursor.hasNext()) {
				DBObject obj = cursor.next();
				String idr = (String) obj.get("idr");
				String dataType = (String) obj.get("data_type");

				Map<String, Object> data = (Map<String, Object>) obj.get("data");
				Map<String, Object> place = (Map<String, Object>) data.get("place");
				
				// profile
				if(idr.startsWith("profile") && "PROFILE".equalsIgnoreCase(dataType)){
					new UserProfileParser().parseUserInfo(obj);
				}
				
				// my statuses
				if (idr.startsWith("status")
						&& dataType.equalsIgnoreCase("status")) {
					FBPost pst = extractPostData(obj, "tags");
					if (pst != null) {
						post.add(pst);
					}
				}

				// my posts
				if (idr.contains("post") && dataType.equalsIgnoreCase("post")) {
					if(null!=place){
						FBCheckIn ckin = extractCheckinData(obj);
						if (null != ckin) {
							checkIn.add(ckin);
						}
					} else {
						FBPost pst = extractPostData(obj, "with_tags");
						if (pst != null) {
							post.add(pst);
						}	
					}
					
				}

				// Posts/check ins where user am tagged.
				if ((idr.startsWith("photo") || idr.startsWith("status"))
						&& dataType.equalsIgnoreCase("feed")) {
					// check if i am tagged.
					boolean isUserTagged = checkIfUserIsTagged(data, user);
					if (isUserTagged) {
						// check if it has place
						if (null != place && !place.isEmpty()) {
							FBCheckIn ckin = extractCheckinData(obj);
							if (ckin != null) {
								checkIn.add(ckin);
							}
						} else {
							FBPost pst = extractPostData(obj, "with_tags");
							if (null != pst) {
								post.add(pst);
							}
						}
					}
				}
			}
			fData.setCheckIn(checkIn);
			fData.setPost(post);
		} else {
			System.out.println("Collection is null.");
			throw new Exception("Collection found null.");
		}
		System.out.println("Total posts : " + fData.getPost().size()
				+ " checkins : " + fData.getCheckIn().size());
		return fData;
	}

	private boolean checkIfUserIsTagged(Map<String, Object> data, String user) {
		boolean resp = false;
		Set<String> withWhom = getTagsInfo(data, "with_tags");
		if (withWhom.contains(user)) {
			resp = true;
		}
		return resp;
	}

	@SuppressWarnings("unchecked")
	private FBCheckIn extractCheckinData(DBObject obj) throws ParseException {
		FBCheckIn checkIn = null;
		Map<String, Object> data = (Map<String, Object>) obj.get("data");
		Map<String, Object> place = (Map<String, Object>) data.get("place");
		if (null != place && !place.isEmpty()) {
			System.out.println("CHECK IN: " + data);
			Place fPlace = getPlaceInfo(place);

			List<String> commentedBy = new ArrayList<String>();
			List<String> likedBy = new ArrayList<String>();
			Set<String> withWhom = new HashSet<String>();

			// comments
			Map<String, Object> comments = (Map<String, Object>) data
					.get("comments");
			commentedBy = getCommentsInfo(comments);

			// likes
			Map<String, Object> likes = (Map<String, Object>) data.get("likes");
			likedBy = getLikesInfo(likes);
			
			// tags
			withWhom = getTagsInfo(data, "with_tags");
			checkIn = new FBCheckIn();
			checkIn.setCommentedBy(new HashSet<String>(commentedBy));
			
			Date jDate = getActivityDateTime(null, data, false);
			Calendar cal = Calendar.getInstance();
            cal.setTime(jDate);
            checkIn.setDate(cal.get(Calendar.DATE));
            checkIn.setMonth(cal.get(Calendar.MONTH) + 1);
            checkIn.setYear(cal.get(Calendar.YEAR));
            
			checkIn.setLikedBy(new HashSet<String>(likedBy));
			checkIn.setNumberOfComments(Long.valueOf(commentedBy.size()));
			checkIn.setNumberOfLikes(Long.valueOf(likedBy.size()));
			checkIn.setPicture((String) (data.get("picture")));
			checkIn.setPlace(fPlace);
			checkIn.setWithWhom(withWhom);
			String msg = String.valueOf(data.get("message"));
			msg = msg.replace("null", "");
			System.out.println("Message : "+msg);
			checkIn.setMessage(msg);
		}

		if (checkIn != null) {
			System.out.println(checkIn.toString());
		}
		return checkIn;
	}

	private Date getActivityDateTime(DBObject obj, Map<String, Object> data,
			boolean isPost) throws ParseException {
		Date jdt = new Date();
		String fbDate = null;
		//if (!isPost) {
			fbDate = String.valueOf(data.get("created_time"));
			if(null==fbDate || fbDate.isEmpty() || "null".equalsIgnoreCase(fbDate)){
				fbDate = String.valueOf(data.get("updated_time"));
			}
			SimpleDateFormat incomingFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
			jdt= incomingFormat.parse(fbDate);
//		} else {
//			jdt = (Date) obj.get("time");
//		}
		return jdt;
	}

	@SuppressWarnings("unchecked")
	private Place getPlaceInfo(Map<String, Object> place) {
		Map<String, String> location = (Map<String, String>) place
				.get("location");
		Place fPlace = new Place();
		fPlace.setCity(location.get("city"));
		fPlace.setCountry(location.get("country"));
		fPlace.setName(String.valueOf(place.get("name")));
		fPlace.setState(location.get("state"));
		fPlace.setStreet(location.get("street"));
		return fPlace;
	}

	@SuppressWarnings("unchecked")
	private FBPost extractPostData(DBObject obj, String tagName) throws ParseException {
		FBPost post = null;
		
		List<String> commentedBy = new ArrayList<String>();
		List<String> likedBy = new ArrayList<String>();
		Set<String> withWhom = new HashSet<String>();

		Map<String, Object> data = (Map<String, Object>) obj.get("data");
		if(! data.containsKey("story")) {
			post = new FBPost();
			System.out.println("POST : " + obj);
			// comments
			Map<String, Object> comments = (Map<String, Object>) data
					.get("comments");
			commentedBy = getCommentsInfo(comments);

			// likes
			Map<String, Object> likes = (Map<String, Object>) data.get("likes");
			likedBy = getLikesInfo(likes);

			// tags
			withWhom = getTagsInfo(data, tagName);

			// description
			String desc = String.valueOf(data.get("description"));
			String msg = String.valueOf(data.get("message"));
			StringBuffer completeMsg = new StringBuffer();
			if(null != desc){
				msg = msg.replace("null", "");
				completeMsg.append(msg);
				completeMsg.append(" ");
				completeMsg.append(desc);
				msg = completeMsg.toString();
			}
			System.out.println("Message : "+msg);
			post.setCommentedBy(new HashSet<String>(commentedBy));
			
			Date jDate = getActivityDateTime(obj, data, false);
			Calendar cal = Calendar.getInstance();
            cal.setTime(jDate);
            post.setDate(cal.get(Calendar.DATE));
            post.setMonth(cal.get(Calendar.MONTH) + 1);
            post.setYear(cal.get(Calendar.YEAR));
            
			
			post.setLikedBy(new HashSet<String>(likedBy));
			post.setMessage(msg);
			post.setNumberOfComments(Long.valueOf(commentedBy.size()));
			post.setNumberOfLikes(Long.valueOf(likedBy.size()));
			post.setWithWhom(withWhom);
			post.setPicture(String.valueOf(data.get("picture")));
			System.out.println(post.toString());
		}
		return post;
	}

	@SuppressWarnings("unchecked")
	private Set<String> getTagsInfo(Map<String, Object> data, String tagName) {
		Set<String> withWhom = new HashSet<String>();
		Map<String, Object> tags = (Map<String, Object>) data.get(tagName);
		if (null != tags && !tags.isEmpty()) {
			BasicDBList tData = (BasicDBList) tags.get("data");
			for (int i = 0; i < tData.size(); i++) {
				BasicDBObject from = (BasicDBObject) tData.get(i);
				withWhom.add(from.getString("name"));
			}
		}
		return withWhom;
	}

	private List<String> getLikesInfo(Map<String, Object> likes) {
		List<String> likedBy = new ArrayList<String>();
		if (null != likes && !likes.isEmpty()) {
			BasicDBList lData = (BasicDBList) likes.get("data");
			for (int i = 0; i < lData.size(); i++) {
				BasicDBObject from = (BasicDBObject) lData.get(i);
				likedBy.add(from.getString("name"));
			}
		}
		return likedBy;
	}

	@SuppressWarnings("unchecked")
	private List<String> getCommentsInfo(Map<String, Object> comments) {
		List<String> commentedBy = new ArrayList<String>();
		if (null != comments && !comments.isEmpty()) {
			BasicDBList cData = (BasicDBList) comments.get("data");
			for (int i = 0; i < cData.size(); i++) {
				Map<String, Object> from = (Map<String, Object>) cData.get(i);
				BasicDBObject fromObj = (BasicDBObject) from.get("from");
				commentedBy.add(fromObj.getString("name"));
			}
		}
		return commentedBy;
	}
}
