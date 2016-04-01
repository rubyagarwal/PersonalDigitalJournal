package jsonwritter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import personal.journal.controller.DataRetrievalHelper;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class JsonWrite {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		Date start = new Date(System.currentTimeMillis());
		start.setDate(1);
		start.setMonth(0);
		start.setYear(90);
		

		Date end = new Date(System.currentTimeMillis());
		end.setDate(1);
		end.setMonth(4);
		end.setYear(114);

		BasicDBObject dbo = new DataRetrievalHelper().retrieveData(start, end,
				false);
		BasicDBList fb = (BasicDBList) dbo.get("fb");
		BasicDBList fs = (BasicDBList) dbo.get("fs");
		BasicDBList tw = (BasicDBList) dbo.get("tw");

		JSONObject timeParent = new JSONObject();
		JSONObject timeChild = new JSONObject();
		timeChild.put("headline", "Digital Journal");
		timeChild.put("type", "default");
		timeChild.put("text", "<h3>Ruby Agarwal</h3>");
		timeChild.put("startDate", "2009,1,14");

		JSONObject onetimeAsset = new JSONObject();
		// Map<String,String> assets= new HashMap<String, String>();
		onetimeAsset.put("media", "assets/img/notes.png");
		onetimeAsset.put("credit", "");
		onetimeAsset.put("caption", "");
		timeChild.put("asset", onetimeAsset);

		JSONArray list = new JSONArray();

		for (int i = 0; i < fb.size(); i++) {
			JSONObject entries = new JSONObject();
			DBObject o = (DBObject) fb.get(i);
			entries.put("headline", "Post");
			StringBuffer d = new StringBuffer();
			d.append(String.valueOf(o.get("year")));
			d.append(",");
			d.append(String.valueOf(o.get("month")));
			d.append(",");
			d.append(String.valueOf(o.get("date")));

			entries.put("startDate", String.valueOf(d.toString()));
			entries.put("text", "<h3>"+String.valueOf(o.get("message"))+"</h3></ br><p style = \"color:white;\" >Likes " + Long.valueOf(String.valueOf(o.get("numberOfLikes")))+ "</p>");

			JSONObject perEntryAsset = new JSONObject();
			// Map<String,String> assets= new HashMap<String, String>();
			perEntryAsset.put(
					"media",
					String.valueOf(o.get("picture")) == null ? "" : String
							.valueOf(o.get("picture")));
			perEntryAsset.put("credit", "");
			perEntryAsset.put("caption", "");
			entries.put("asset", perEntryAsset);

			list.add(entries);
		}

		for (int i = 0; i < fs.size(); i++) {
			JSONObject entries = new JSONObject();
			DBObject o = (DBObject) fs.get(i);
			entries.put("headline", "checkin");
			StringBuffer d = new StringBuffer();
			d.append(String.valueOf(o.get("year")));
			d.append(",");
			d.append(String.valueOf(o.get("month")));
			d.append(",");
			d.append(String.valueOf(o.get("date")));

			entries.put("startDate", String.valueOf(d.toString()));
			entries.put("text", "<h3>"+String.valueOf(o.get("message"))+"</h3><br>Likes<br/>");

			JSONObject perEntryAsset = new JSONObject();
			// Map<String,String> assets= new HashMap<String, String>();
			perEntryAsset.put(
					"media",
					String.valueOf(o.get("picture")) == null ? "" : String
							.valueOf(o.get("picture")));
			perEntryAsset.put("credit", "");
			perEntryAsset.put("caption", "");
			entries.put("asset", perEntryAsset);

			list.add(entries);
		}

		for (int i = 0; i < tw.size(); i++) {
			JSONObject entries = new JSONObject();
			DBObject o = (DBObject) tw.get(i);
			entries.put("headline", "tweet");
			StringBuffer d = new StringBuffer();
			d.append(String.valueOf(o.get("year")));
			d.append(",");
			d.append(String.valueOf(o.get("month")));
			d.append(",");
			d.append(String.valueOf(o.get("date")));

			entries.put("startDate", String.valueOf(d.toString()));
			entries.put("text", "<h3>"+ String.valueOf(o.get("message"))+"</h3><br>Likes");

			JSONObject perEntryAsset = new JSONObject();
			// Map<String,String> assets= new HashMap<String, String>();
			perEntryAsset.put(
					"media",
					String.valueOf(o.get("picture")) == null ? "" : String
							.valueOf(o.get("picture")));
			perEntryAsset.put("credit", "");
			perEntryAsset.put("caption", "");
			entries.put("asset", perEntryAsset);

			list.add(entries);
		}
		timeChild.put("date", list);
		timeParent.put("timeline", timeChild);

		try {

			FileWriter file = new FileWriter("I:\\PersonalJournalWS\\PJServlet\\WebContent\\data.json");
			file.write(timeParent.toJSONString());
			file.flush();
			file.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.print(timeParent);

	}

}
