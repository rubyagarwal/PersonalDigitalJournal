package personal.journal.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import core.JournalConstants;
import core.MongoHelper;
import data.Statistics;

public class DataRetrievalHelper {

	private static BasicDBList fbDataList = new BasicDBList();
	private static BasicDBList fsDataList = new BasicDBList();
	private static BasicDBList twData = new BasicDBList();
	private static Statistics stats = new Statistics();

	@SuppressWarnings("deprecation")
	public BasicDBObject retrieveData(Date start, Date end, boolean isDefault) {
		//		isDefault = false;
		if (!isDefault) {
			System.out.println("NOT DEFAULT");
			//			start = new Date(System.currentTimeMillis());
			//			start.setDate(9);
			//			start.setMonth(1);
			//			start.setYear(2014);
			//
			//			end = new Date(System.currentTimeMillis());
			//			end.setDate(29);
			//			end.setMonth(4);
			//			end.setYear(2014);
		} else {
			System.out.println("DEFAULT");
			// use todays date as start and end
			start = new Date(System.currentTimeMillis());
			end = new Date(System.currentTimeMillis());
		}
		DBCursor fbCursor = getCursorForCollection(JournalConstants.LOCALHOST,
				JournalConstants.DB_NAME, JournalConstants.DEST_FACEBOOK_DATA,
				start, end,isDefault);
		DBCursor fsCursor = getCursorForCollection(JournalConstants.LOCALHOST,
				JournalConstants.DB_NAME,
				JournalConstants.DEST_FOURSQUARE_DATA, start, end,isDefault);
		DBCursor twCursor = getCursorForCollection(JournalConstants.LOCALHOST,
				JournalConstants.DB_NAME, JournalConstants.DEST_TWITTER_DATA,
				start, end,isDefault);
		DBCursor userCursor = getCursorForCollection(
				JournalConstants.LOCALHOST, JournalConstants.DB_NAME,
				JournalConstants.USERS_DATA, start, end,isDefault);

		BasicDBList fbData = getDataListForCursor(fbCursor);
		BasicDBList posts = getPosts(fbData);
		BasicDBList checkIns = getCheckIns(fbData); 
		setFbDataList(posts);
		BasicDBList fsData = getDataListForCursor(fsCursor);
		for(int i=0;i<checkIns.size();i++){
			fsData.add(checkIns.get(i));
		}
		setFsDataList(fsData);
		BasicDBList twDataList = getDataListForCursor(twCursor);
		BasicDBList userDataList = getDataListForCursor(userCursor);
		setTwData(getTwitterDataListForCursor(twDataList, userDataList, start,
				end));

		BasicDBObject dbo = new BasicDBObject();
		dbo.put("fb", fbDataList);
		dbo.put("fs", fsDataList);
		dbo.put("tw", twData);
		dbo.put("cnt", Math.ceil((fbDataList.size()+fsDataList.size()+twData.size())/3));

		System.out.println("FB data " + fbDataList);
		System.out.println("FS data " + fsDataList);
		System.out.println("TW data " + twData);

		setStats(createStats());
		return dbo;

	}

	private BasicDBList getCheckIns(BasicDBList fbData) {
		BasicDBList checkins = new BasicDBList();
		for(int i = 0; i < fbData.size() ; i++){
			DBObject o = (DBObject) fbData.get(i);
			if(null != o.get("place")){
				checkins.add(fbData.get(i));
			}
		}
		return checkins;
	}

	private BasicDBList getPosts(BasicDBList fbData) {
		BasicDBList posts = new BasicDBList();
		for(int i = 0; i < fbData.size() ; i++){
			DBObject o = (DBObject) fbData.get(i);
			if(null == o.get("place")){
				posts.add(fbData.get(i));
			}
		}
		return posts;
	}

	private BasicDBList getTwitterDataListForCursor(BasicDBList twDataList,
			BasicDBList userDataList, Date start, Date end) {
		BasicDBList twData = new BasicDBList();
		// getscreen names
		// for screenanme get tweets
		// append username

		for (int i = 0; i < userDataList.size(); i++) {
			DBObject user = (DBObject) userDataList.get(i);
			String scName = (String) user.get("screenName");
			for (int j = 0; j < twDataList.size(); j++) {
				DBObject twM = (DBObject) twDataList.get(j);
				BasicDBList tweets = (BasicDBList) twM.get(scName);
				if (tweets != null) {
					for (int k = 0; k < tweets.size(); k++) {
						DBObject tweet = (DBObject) tweets.get(i);
						tweet.put("user", user);
						if (checkIfValidDate(start, end, tweet)) {
							twData.add(tweet);
						}
					}
				}

			}
		}

		return twData;
	}

	@SuppressWarnings("deprecation")
	private boolean checkIfValidDate(Date s, Date e, DBObject tweet) {
		boolean resp = false;
		Integer date = (Integer) tweet.get("date");
		Integer month = (Integer) tweet.get("month");
		Integer year = (Integer) tweet.get("year");
		// twitter date
		Date dt = new Date();
		dt.setDate(date.intValue());
		dt.setMonth(month.intValue());
		dt.setYear(year.intValue());

		// local start and end
		Date ls = new Date();
		ls.setDate(s.getDate() - 1);
		ls.setMonth(s.getMonth());
		ls.setYear(s.getYear());

		Date le = new Date();
		le.setDate(e.getDate() + 1);
		le.setMonth(e.getMonth());
		le.setYear(e.getYear());
		// System.out.println("validating: " + dt.getDate() +"/" + dt.getMonth()
		// + "/" + dt.getYear());
		// System.out.println("from: " + ls.getDate() + "/" + ls.getMonth() +
		// "/" + ls.getYear());
		// System.out.println("to: " + le.getDate() + "/" + le.getMonth() + "/"
		// + le.getYear());

		if (dt.after(ls) && dt.before(le)) {
			resp = true;
		}
		return resp;
	}

	private BasicDBList getDataListForCursor(DBCursor cursor) {
		System.out.println("Results : " + cursor.size());
		BasicDBList dataList = new BasicDBList();

		while (cursor.hasNext()) {
			DBObject dbo = cursor.next();
			dataList.add(dbo);
		}

		return dataList;
	}

	@SuppressWarnings("deprecation")
	private DBCursor getCursorForCollection(String host, String db,
			String collection, Date start, Date end, boolean isDef) {
		DBCollection dbCollection = MongoHelper.readCollectionFromDb(host, db,
				collection);
		DBCursor cursor;

		if (JournalConstants.USERS_DATA.equalsIgnoreCase(collection)
				|| JournalConstants.DEST_TWITTER_DATA.equals(collection)) {
			System.out.println("Users/twitter data cursor");
			cursor = dbCollection.find();
		} else {
			if(isDef){
				System.out.println("Default");
				DBObject query = new BasicDBObject();
				query.put("year", new BasicDBObject("$gte", 2014)
				.append("$lte", 2014));
				query.put("month", new BasicDBObject("$gte", 4)
				.append("$lte", 4));
				query.put("date", new BasicDBObject("$gte", 1)
				.append("$lte", 31));
				cursor = dbCollection.find(query).sort(
						new BasicDBObject("year", -1).append("month", -1).append(
								"date", -1));

			} else {
				System.out.println(start.getYear());
				System.out.println(end.getYear());
				int sm=start.getMonth();
				int em = end.getMonth();
				DBObject query = new BasicDBObject();
				query.put("year", new BasicDBObject("$gte", start.getYear())
				.append("$lte", end.getYear()));
				query.put("month", new BasicDBObject("$gte", sm+1)
				.append("$lte", em+1));
				query.put("date", new BasicDBObject("$gte", start.getDate())
				.append("$lte", end.getDate()));
				cursor = dbCollection.find(query).sort(
						new BasicDBObject("year", -1).append("month", -1).append(
								"date", -1));
			}

		}
		return cursor;
	}

	public static Statistics createStats() {
		Statistics s = new Statistics();
		InformationExtractor ie = new InformationExtractor();
		System.out.println("fsDataList "+fsDataList +" fbdata " + fbDataList);
		// total activities
		Long totalPsts = 0l;
		Long totalCks = Long.valueOf(fbDataList.size());

		for (int i = 0; i < fbDataList.size(); i++) {
			DBObject info = (DBObject) fbDataList.get(i);
			System.out.println("INFO : "+info);
			if (null == info.get("place")) {
				totalPsts=totalPsts+1;
			} else {
				totalCks=totalCks+1;
			}
		}
		totalCks=totalCks+fsDataList.size();
		Long totalActs = totalCks + totalPsts + Long.valueOf(twData.size());
		if(totalActs == 0){
			totalActs=1l;
		}
		System.out.println("ck " + totalCks + "ps " + totalPsts + " tw "
				+ twData.size() + " T " + totalActs);
		s.setTotalCheckins((totalCks * 100) / totalActs);
		s.setTotalPosts((totalPsts * 100) / totalActs);
		s.setTotalTweets((twData.size() * 100) / totalActs);

		// max with tags
		Map<String, Integer> withDataMap = new HashMap<String, Integer>();
		withDataMap.putAll(ie.createMapFromWithTags(
				fbDataList, true));
		Map<String, Integer> fsMap = new InformationExtractor()
		.createMapFromWithTags(fsDataList, false);

		System.out.println("before withDataMap : "+withDataMap);
		for (Entry<String, Integer> ent : fsMap.entrySet()) {
			if (withDataMap.containsKey(ent.getKey())) {
				Integer i = withDataMap.get(ent.getKey());
				i+=ent.getValue();
				System.out.println(" I " + i);
				withDataMap.put(ent.getKey(), i);
			} else {
				withDataMap.put(ent.getKey(), ent.getValue());
			}
		}

		System.out.println("withDataMap : "+withDataMap);
		List<Entry<String, Integer>> max = new InformationExtractor()
		.entriesSortedByValues(withDataMap);
		if(null!=max && !max.isEmpty()){
			Entry<String, Integer> maxWith = max.get(0);
			s.setWithWhomMax(maxWith.getKey());
			s.setWithWhomMaxCount(maxWith.getValue());
			System.out.println("Max count " + s.getWithWhomMaxCount()
					+ " max name " + s.getWithWhomMax());
		}
		// top likers
		StringBuffer topLks=new StringBuffer();
		Map<String,Integer> fbLikersMap = new HashMap<String,Integer>();
		Map<String,Integer> fsLikersMap = new HashMap<String,Integer>();

		fbLikersMap.putAll(ie.createTopLikersMap(fbDataList));
		fsLikersMap.putAll(ie.createTopLikersMap(fsDataList));
		System.out.println("FS LIKES "+fsLikersMap);
		System.out.println("FB LIKES "+fbLikersMap);
		Map<String,Integer> likersMap = new HashMap<String,Integer>(fbLikersMap);

		for (Entry<String, Integer> ent : fsLikersMap.entrySet()) {
			if (likersMap.containsKey(ent.getKey())) {
				Integer i = likersMap.get(ent.getKey());
				i+=ent.getValue();
				likersMap.put(ent.getKey(), i);
			} else {
				likersMap.put(ent.getKey(), ent.getValue());
			}
		}
		List<Entry<String, Integer>> maxLikersMap = ie.entriesSortedByValues(likersMap);
		System.out.println("sorted LIKERS MAP "+maxLikersMap);
		int itr = 0;
		for(Entry<String, Integer> e : maxLikersMap){
			if(itr < 3) {
				StringBuffer bf = new StringBuffer();
				bf.append(e.getKey());
				bf.append(":");
				bf.append(e.getValue()); //total
				bf.append(":");
				bf.append(fsLikersMap.get(e.getKey())==null?0:fsLikersMap.get(e.getKey()));
				bf.append(":");
				bf.append(fbLikersMap.get(e.getKey())==null?0:fbLikersMap.get(e.getKey())); 
				System.out.println("Top likes " + bf.toString());

				topLks.append(bf.toString());
				topLks.append(",");
				System.out.println(topLks);
				itr++;
			}
		}
		s.setTopLikers(String.valueOf(topLks));
		System.out.println("Stats "+s.toString());


		// top commenters
		StringBuffer topCms=new StringBuffer();
		Map<String,Integer> fbCmsMap = new HashMap<String,Integer>();
		Map<String,Integer> fsCmsMap = new HashMap<String,Integer>();

		fbCmsMap.putAll(ie.createTopCmsMap(fbDataList));
		fsCmsMap.putAll(ie.createTopCmsMap(fsDataList));
		System.out.println("FS CMS "+fsCmsMap);
		System.out.println("FB CMS "+fbCmsMap);
		Map<String,Integer> cmsMap = new HashMap<String,Integer>(fbCmsMap);

		for (Entry<String, Integer> ent : fsCmsMap.entrySet()) {
			if (cmsMap.containsKey(ent.getKey())) {
				Integer i = cmsMap.get(ent.getKey());
				i+=ent.getValue();
				cmsMap.put(ent.getKey(), i);
			} else {
				cmsMap.put(ent.getKey(), ent.getValue());
			}
		}
		List<Entry<String, Integer>> maxCmsMap = ie.entriesSortedByValues(cmsMap);
		System.out.println("sorted CMS MAP "+maxCmsMap);
		int itr1 = 0;
		for(Entry<String, Integer> e : maxCmsMap){
			if(itr1 < 3) {
				StringBuffer bf = new StringBuffer();
				bf.append(e.getKey());
				bf.append(":");
				bf.append(e.getValue()); //total
				bf.append(":");
				bf.append(fsCmsMap.get(e.getKey())==null?0:fsCmsMap.get(e.getKey()));
				bf.append(":");
				bf.append(fbCmsMap.get(e.getKey())==null?0:fbCmsMap.get(e.getKey())); 
				System.out.println("Top cms " + bf.toString());

				topCms.append(bf.toString());
				topCms.append(",");
				System.out.println(topCms);
				itr1++;
			}
		}
		s.setTopCommenters(String.valueOf(topCms));
		System.out.println("Stats "+s.toString());
		return s;

	}

	public BasicDBList getFbDataList() {
		return fbDataList;
	}

	public void setFbDataList(BasicDBList fbDataList) {
		this.fbDataList = fbDataList;
	}

	public BasicDBList getFsDataList() {
		return fsDataList;
	}

	public void setFsDataList(BasicDBList fsDataList) {
		this.fsDataList = fsDataList;
	}

	public BasicDBList getTwData() {
		return twData;
	}

	public void setTwData(BasicDBList twData) {
		this.twData = twData;
	}

	public static Statistics getStats() {
		return stats;
	}

	public static void setStats(Statistics stats) {
		DataRetrievalHelper.stats = stats;
	}

	public BasicDBObject retrieveUserProfile() {
		BasicDBObject profile = new BasicDBObject();
		DBCollection dbCollection = MongoHelper.readCollectionFromDb(JournalConstants.LOCALHOST,
				JournalConstants.DB_NAME, JournalConstants.USER_PROFILE);
		DBCursor cursor = dbCollection.find();
		while(cursor.hasNext()){
			profile = (BasicDBObject) cursor.next();
		}
		System.out.println("PROFILE : " + profile);
		return profile;
	}
}
