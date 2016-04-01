package core;

import java.net.UnknownHostException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;

import data.facebook.FBData;
import data.facebook.UserProfile;
import data.foursquare.FSData;
import data.twitter.TweetsAndUserInfo;

public class MongoHelper {

	public static DB connectToDb(String host, String dbName)
			throws UnknownHostException {
		MongoClient mongoClient = new MongoClient(host);
		return mongoClient.getDB(dbName);
	}

	public static DBCollection readCollectionFromDb(String host, String dbName,
			String collectionName) {
		// connect to db and read collection
		DBCollection collection = null;
		try {
			DB db;
			db = MongoHelper.connectToDb(host, dbName);
			System.out.println("Connected to the db: " + db.getName());
			collection = db.getCollection(collectionName);
			System.out.println("Found collection : " + collection.getName());
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return collection;
	}

	public static void saveFaceBookDocument(String host, String dbName,
			String collName, FBData fbData) throws UnknownHostException {
		String jsonStr = new Gson().toJson(fbData);
		DBCollection coll = getCollectionByName(host, dbName, collName);

		BasicDBObject dataObj = (BasicDBObject) JSON.parse(jsonStr);
		BasicDBList ckList = (BasicDBList) dataObj.get("checkIn");
		BasicDBList ptList = (BasicDBList) dataObj.get("post");

		for (int i = 0; i < ckList.size(); i++) {
			coll.insert((DBObject) ckList.get(i));
		}
		for (int i = 0; i < ptList.size(); i++) {
			coll.insert((DBObject) ptList.get(i));
		}
	}

	public static void saveTwitterDocument(String host, String dbName,
			String collName, String userCollName, TweetsAndUserInfo twtData)
			throws UnknownHostException {
		String jsonStr = new Gson().toJson(twtData);
		DBCollection coll = getCollectionByName(host, dbName, collName);
		DBCollection userColl = getCollectionByName(host, dbName, userCollName);
		BasicDBObject dataObj = (BasicDBObject) JSON.parse(jsonStr);
		BasicDBList userList = (BasicDBList) dataObj.get("users");
		Set<String> screenNames = new HashSet<>();

		for (int i = 0; i < userList.size(); i++) {
			screenNames.add((String) ((BasicDBObject) userList.get(i))
					.get("screenName"));
			userColl.insert((DBObject) userList.get(i));
		}

		BasicDBObject twt = (BasicDBObject) dataObj.get("twtData");
		coll.insert((DBObject) twt);
	}

	public static void saveFourSquareDocument(String host, String dbName,
			String collName, List<FSData> fsData) throws UnknownHostException {
		String jsonStr = new Gson().toJson(fsData);
		DBCollection coll = getCollectionByName(host, dbName, collName);

		BasicDBList data1 = (BasicDBList) JSON.parse(jsonStr);
		for (int i = 0; i < data1.size(); i++) {
			coll.insert((DBObject) data1.get(i));
		}
	}

	private static DBCollection getCollectionByName(String host, String dbName,
			String collName) throws UnknownHostException {
		DBCollection coll = null;
		MongoClient mongoClient = new MongoClient(host);
		DB db = mongoClient.getDB(dbName);
		if (db.collectionExists(collName)) {
			System.out.println("saving data");
			coll = db.getCollection(collName);
		} else {
			System.out.println("creating new coll");
			DBObject options = BasicDBObjectBuilder.start().get();
			coll = db.createCollection(collName, options);
			System.out.println("created");
		}
		return coll;
	}

	public static void saveUserProfileDocument(String host, String dbName,
			String collName, UserProfile profile) throws UnknownHostException {
		String jsonStr = new Gson().toJson(profile);
		DBCollection coll = getCollectionByName(host, dbName, collName);

		BasicDBObject data1 = (BasicDBObject) JSON.parse(jsonStr);
			coll.insert((DBObject) data1);
		
	}
}
