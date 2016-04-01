package parser.foursquare;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import data.Place;
import data.foursquare.FSData;

public class FSDataParser {

	@SuppressWarnings("unchecked")
	public List<FSData> extractFourSquareData(DBCollection collFs)
			throws Exception {
		List<FSData> list = new ArrayList<FSData>();

		if (null != collFs) {
			DBCursor cursor = collFs.find();
			System.out.println("Found number of records: " + cursor.size());
			while (cursor.hasNext()) {
				DBObject json = cursor.next();

				FSData fsData = new FSData();
				Map<String, Object> data = (Map<String, Object>) json
						.get("data");
				String dataType = String.valueOf(json.get("data_type"));
				List<Map<String, Object>> withTag = (List<Map<String, Object>>) data
						.get("with");
				
				String selfCheck="";
				
				if(withTag!=null)
				{
					for(Map<String, Object> ppl1 : withTag)
					{
						if((ppl1.get("relationship"))=="self")
						{
							selfCheck="self";
							break;
						}
					}
				}
				
				if ((data.get("entities") != null && "CHECKIN".equals(dataType))||(selfCheck=="self")) {

					Map<String, Object> venue = (Map<String, Object>) data
							.get("venue");
					Map<String, Object> likes = (Map<String, Object>) venue
							.get("likes");
					if (likes != null) {
						Long likes_no = Long.valueOf(String.valueOf(likes
								.get("count")));
						fsData.setNumberOfLikes(likes_no);

						List<Map<String, Object>> grpList = new ArrayList<Map<String, Object>>();
						grpList = (List<Map<String, Object>>) likes
								.get("groups");
						Set<String> likeSet = new HashSet<String>();
						if (null != grpList) {
							for (Map<String, Object> grp : grpList) {
								List<Map<String, Object>> items = (List<Map<String, Object>>) grp
										.get("items");
								if (items != null) {
									for (Map<String, Object> item : items) {
										String likename = (String) item
												.get("firstName");
										String lName = (String) item.get("lastName");
										likeSet.add(likename+" "+lName);
									}
								}
							}
						}
						fsData.setLikedBy(likeSet);
					}

					// comments
					Map<String, Object> comments = (Map<String, Object>) data
							.get("comments");
					Long comments_no = Long.valueOf(String.valueOf(comments
							.get("count")));
					fsData.setNumberOfComments(comments_no);

					// shout
					String withwhom = String.valueOf(data.get("shout"));
					withwhom = withwhom.replaceAll("with ", "");
					String[] withArr = withwhom.split(", ");
					Set<String> withSet = new HashSet<String>();
					for (String name : withArr) {
						withSet.add(name);
					}
					fsData.setWithTag(withSet);

					// message
					String msg = String.valueOf(data.get("shout"));
					fsData.setMessage(msg);

					// With Tag on foursquare
				
					Set<String> withTagSet = new HashSet<String>();
					if (withTag != null) {
						for (Map<String, Object> ppl : withTag) {
							String withfn = (String) ppl.get("firstName");
							String withln = (String) ppl.get("lastName");
							StringBuffer nameBuf = new StringBuffer(withfn);
							nameBuf.append(" ");
							nameBuf.append(withln);
							withTagSet.add(nameBuf.toString());
						}
					}
					fsData.setWithTag(withTagSet);

					// place
					Map<String, Object> location = (Map<String, Object>) venue
							.get("location");
					Place fsPlace = new Place();
					fsPlace.setName(String.valueOf(venue.get("name")));
					fsPlace.setStreet(String.valueOf(location.get("address")));
					fsPlace.setCity(String.valueOf(location.get("city")));
					fsPlace.setState(String.valueOf(location.get("state")));
					fsPlace.setCountry(String.valueOf(location.get("country")));
					fsData.setPlace(fsPlace);

					// date
					String dateString = String.valueOf(json.get("time"));
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
					dateFormat.setLenient(false);
					Date jDate = dateFormat.parse(dateString);
					//fsData.setDate(jDate);
					
					 Calendar cal = Calendar.getInstance();
                     cal.setTime(jDate);
                     
                     int day = cal.get(Calendar.DATE);
                     fsData.setDate(day);
                     
                     int montha = cal.get(Calendar.MONTH);
                     int month=montha+1;
                     fsData.setMonth(month);
                     
                     int year = cal.get(Calendar.YEAR);
                     fsData.setYear(year);
					list.add(fsData);
				}
			} // for
		}
		System.out.println("List size : " + list.size());
		return list;
	}

}
