package util;

import parser.facebook.FBDataParser;

import com.mongodb.DBCollection;

import core.JournalConstants;
import core.MongoHelper;
import data.facebook.FBData;

public class RunParser {

	public static void main(String[] args) {
		try {
			DBCollection fbColl = MongoHelper.readCollectionFromDb(
					JournalConstants.LOCALHOST, JournalConstants.DB_NAME,
					JournalConstants.SOURCE_FACEBOOK_DATA);
			FBData fb = new FBDataParser().extractRelevantFbData(fbColl,
					JournalConstants.LOGGED_IN_USER);
			System.out.println("FB DATA : " + fb.toString());
//			MongoHelper.saveFaceBookDocument(JournalConstants.LOCALHOST,
//					JournalConstants.DB_NAME,
//					JournalConstants.DEST_FACEBOOK_DATA, fb);

//			 DBCollection twColl = MongoHelper.readCollectionFromDb(
//			 JournalConstants.LOCALHOST, JournalConstants.DB_NAME,
//			 JournalConstants.SOURCE_TWITTER_DATA);
//			 TweetsAndUserInfo twtData = new
//			 TwitterDataParser()
//			 .extractTwitterData(twColl);
//			 System.out.println("TW DATA : " + twtData.toString());
//			 MongoHelper.saveTwitterDocument(JournalConstants.LOCALHOST,
//						JournalConstants.DB_NAME,
//						JournalConstants.DEST_TWITTER_DATA, JournalConstants.USERS_DATA, twtData);
			 
//////			 
//			 DBCollection fsColl = MongoHelper.readCollectionFromDb(
//			 JournalConstants.LOCALHOST, JournalConstants.DB_NAME,
//			 JournalConstants.SOURCE_FOURSQUARE_DATA);
//			 List<FSData> list = new FSDataParser()
//			 .extractFourSquareData(fsColl);
//			 System.out.println("FS DATA : " + list.toString());
//
//			MongoHelper.saveFourSquareDocument(JournalConstants.LOCALHOST,
//					JournalConstants.DB_NAME,
//					JournalConstants.DEST_FOURSQUARE_DATA,list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
