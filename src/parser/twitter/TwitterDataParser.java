package parser.twitter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import data.twitter.TweetsAndUserInfo;
import data.twitter.TwitterData;
import data.twitter.UserData;

public class TwitterDataParser {

	@SuppressWarnings("unchecked")
	public TweetsAndUserInfo extractTwitterData(
			DBCollection collTwt) throws ParseException {

		TweetsAndUserInfo info = new TweetsAndUserInfo();
		Map<String, List<TwitterData>> mapData = new HashMap<String, List<TwitterData>>();
		Set<UserData> users = new HashSet<UserData>();

		if (null != collTwt) {
			System.out.println("Found collection for twitter. read now.");
			DBCursor cursor = collTwt.find();
			System.out.println("Found number of records: " + cursor.size());

			while (cursor.hasNext()) {

				DBObject jsonObject = cursor.next();
				Map<String, Object> data = (Map<String, Object>) jsonObject
						.get("data");

				if (null != data) {

					String dataType = (String) jsonObject.get("data_type");
					if (dataType.equals("TWEET") || dataType.equals("MENTION")) {
						TwitterData tweet = getTweet(data,
								dataType.equals("TWEET") ? true : false);
						UserData userData = getUserData(data);
						users.add(userData);
						if (mapData.containsKey(userData.getScreenName())) {
							List<TwitterData> twitterDataList = mapData
									.get(userData.getScreenName());
							twitterDataList.add(tweet);
							mapData.put(userData.getScreenName(), twitterDataList);
						} else {
							List<TwitterData> twitterDataList = new ArrayList<TwitterData>();
							twitterDataList.add(tweet);
							mapData.put(userData.getScreenName(), twitterDataList);
						}
					} // if
				}
			}
		}

		info.setTwtData(mapData);
		info.setUsers(new ArrayList<>(users));
		return info;
	}

	@SuppressWarnings("unchecked")
	private static UserData getUserData(Map<String, Object> data) {
		UserData userData = new UserData();
		Map<String, Object> user = (Map<String, Object>) data.get("user");
		userData.setLocation(String.valueOf(user.get("location")));
		userData.setName(String.valueOf(user.get("name")));
		userData.setNoOfFollowers(Long.valueOf(String.valueOf(user
				.get("followers_count"))));
		userData.setNoOfFriends(Long.valueOf(String.valueOf(user
				.get("friends_count"))));
		userData.setNoOfTweetsLiked(Long.valueOf(String.valueOf(user
				.get("favourites_count"))));
		userData.setScreenName(String.valueOf(user.get("screen_name")));
		System.out.println(userData.getUser());
		return userData;
	}

	@SuppressWarnings("unchecked")
	private static TwitterData getTweet(Map<String, Object> data,
			boolean yourTweet) throws ParseException {
		TwitterData tweet = new TwitterData();
		Map<String, Object> entities = (Map<String, Object>) data
				.get("entities");
		BasicDBList hashtags = (BasicDBList) entities.get("hashtags");
		Set<String> hashtag = new HashSet<String>();
		for (int i = 0; i < hashtags.size(); i++) {
			BasicDBObject tag = (BasicDBObject) hashtags.get(i);
			hashtag.add(String.valueOf(tag.get("text")));
		}

		String dateString = String.valueOf(data.get("created_at"));
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
		dateFormat.setLenient(false);
		Date jDate = dateFormat.parse(dateString);
//		tweet.setDate(jDate);
		
		Calendar cal = Calendar.getInstance();
        cal.setTime(jDate);
        tweet.setDate(cal.get(Calendar.DATE));
        tweet.setMonth(cal.get(Calendar.MONTH) + 1);
        tweet.setYear(cal.get(Calendar.YEAR));
		
		tweet.setFavoriteCount(Long.valueOf(String.valueOf(data
				.get("favorite_count"))));
		tweet.setHashtag(hashtag);
		tweet.setMessage(String.valueOf(data.get("text")));
		tweet.setRetweetCount(Long.valueOf(String.valueOf(data
				.get("retweet_count"))));
		tweet.setyourTweet(yourTweet);
		System.out.println(tweet);
		return tweet;
	}

}