package data.twitter;

import java.util.List;
import java.util.Map;

public class TweetsAndUserInfo {

	List<UserData> users;
	Map<String, List<TwitterData>> twtData;
	public List<UserData> getUsers() {
		return users;
	}
	public Map<String, List<TwitterData>> getTwtData() {
		return twtData;
	}
	public void setUsers(List<UserData> users) {
		this.users = users;
	}
	public void setTwtData(Map<String, List<TwitterData>> twtData) {
		this.twtData = twtData;
	}
	@Override
	public String toString() {
		return "TweetsAndUserInfo [users=" + users + ", twtData=" + twtData
				+ "]";
	}
	
	
}
