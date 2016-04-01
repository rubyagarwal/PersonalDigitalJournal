package parser.facebook;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import core.JournalConstants;
import core.MongoHelper;
import data.facebook.Education;
import data.facebook.UserProfile;
import data.facebook.Work;

public class UserProfileParser {

	// parse info
	// persist
	public void parseUserInfo(DBObject obj) throws UnknownHostException {
		UserProfile profile = new UserProfile();
		Map<String, Object> data = (Map<String, Object>) obj.get("data");
		if (null != data) {
			String bio = String.valueOf(data.get("bio"));
			String name = String.valueOf(data.get("name"));
			String email = String.valueOf(data.get("email"));
			String bday = String.valueOf(data.get("birthday"));
			String gender = String.valueOf(data.get("gender"));

			Map<String, String> homeTownMap = (Map<String, String>) data
					.get("hometown");
			String homeTown = homeTownMap.get("name");

			List<Work> work = new ArrayList<>();
			BasicDBList workList = (BasicDBList) data.get("work");
			for (int i = 0; i < workList.size(); i++) {
				DBObject o = (DBObject) workList.get(i);
				Work w = new Work();
				w.setStartDate(String.valueOf(o.get("start_date")));
				w.setEndDate(String.valueOf(o.get("end_date")));
				Map<String, String> emp = (Map<String, String>) o
						.get("employer");
				w.setEmployer(String.valueOf(emp.get("name")));
				Map<String, String> pos = (Map<String, String>) o
						.get("position");
				w.setPosition(String.valueOf(pos.get("name")));
				Map<String, String> loc = (Map<String, String>) o
						.get("location");
				w.setLocation(String.valueOf(loc.get("name")));
				work.add(w);
			}

			Map<String, String> loc = (Map<String, String>) data
					.get("location");
			String location = String.valueOf(loc.get("name"));

			List<Education> edu = new ArrayList<>();
			BasicDBList eduList = (BasicDBList) data.get("education");
			for (int i = 0; i < eduList.size(); i++) {
				Education e = new Education();
				DBObject o = (DBObject) eduList.get(i);
				e.setType(String.valueOf(o.get("type")));
				Map<String, String> sch = (Map<String, String>) o.get("school");
				e.setSchool(String.valueOf(sch.get("name")));
				Map<String, String> deg = (Map<String, String>) o.get("degree");
				if (deg != null) {
					e.setDegree(String.valueOf(deg.get("name")) == null ? ""
							: String.valueOf(deg.get("name")));
				}
				Map<String, String> year = (Map<String, String>) o.get("year");
				if (year != null) {
					e.setYear(Integer.valueOf(String.valueOf(year.get("name"))));
				}
				edu.add(e);
			}
			profile.setBday(bday);
			profile.setBio(bio);
			profile.setEdu(edu);
			profile.setEmail(email);
			profile.setGender(gender);
			profile.setHometown(homeTown);
			profile.setLocation(location);
			profile.setName(name);
			profile.setWork(work);
		}
		System.out.println(profile.toString());
		MongoHelper.saveUserProfileDocument(JournalConstants.LOCALHOST,
				JournalConstants.DB_NAME, JournalConstants.USER_PROFILE,
				profile);

	}

}
