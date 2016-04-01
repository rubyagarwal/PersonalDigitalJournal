package personal.journal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import core.JournalConstants;

public class InformationExtractor {

	public Map<String, Integer> createMapFromWithTags(BasicDBList data,
			boolean isFb) {

		Map<String, Integer> withMap = new HashMap<String, Integer>();
		for (int i = 0; i < data.size(); i++) {
			DBObject doc = (DBObject) data.get(i);
			List<String> With = new ArrayList<String>();
			if (isFb) {
				With = (List<String>) doc.get("withWhom");
			} else {
				With = (List<String>) doc.get("withTag");
			}
			if (With != null) {
				for (String names : With) {
					if(! JournalConstants.LOGGED_IN_USER.equalsIgnoreCase(names)) {
						if (withMap.containsKey(names)) {
							Integer temp = withMap.get(names);
							temp++;
							withMap.put(names, temp);
						} else
							withMap.put(names, 1);

					}// for
				}
			}
		}// while
		System.out.println("map with " + withMap);
		return withMap;
	}


	public Map<String, Integer> getMax(Map<String, Integer> MaxinMap) {
		Map<String, Integer> maxKeys = new HashMap<String, Integer>();
		Integer maxValue = Integer.MIN_VALUE;
		for (Map.Entry<String, Integer> sort : MaxinMap.entrySet()) {
			if (sort.getValue() > maxValue) {
				maxKeys.clear();
				maxKeys.put(sort.getKey(), sort.getValue());
				maxValue = sort.getValue();

			} else if (sort.getValue() == maxValue) {
				maxKeys.put(sort.getKey(), sort.getValue());
			}
		}

		return maxKeys;
	}

	public Map<String, Integer> createTopLikersMap(
			BasicDBList data) {
		Map<String,Integer> likeMap = new HashMap<String,Integer>();
		for (int i = 0; i < data.size(); i++) {
			DBObject doc = (DBObject) data.get(i);
			List<String> likes = new ArrayList<String>();
			likes = (List<String>) doc.get("likedBy");
			if (likes != null) {
				for (String names : likes) {
					if(! JournalConstants.LOGGED_IN_USER.equalsIgnoreCase(names)) {
						if (likeMap.containsKey(names)) {
							Integer temp = likeMap.get(names);
							temp++;
							likeMap.put(names, temp);
						} else
							likeMap.put(names, 1);

					}// for
				}
			}
		}
		return likeMap;
	}
	
	<K, V extends Comparable<? super V>> List<Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(
				map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {
			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});

		return sortedEntries;
	}


	public Map<? extends String, ? extends Integer> createTopCmsMap(
			BasicDBList data) {
		Map<String,Integer> cmtMap = new HashMap<String,Integer>();
		for (int i = 0; i < data.size(); i++) {
			DBObject doc = (DBObject) data.get(i);
			List<String> likes = new ArrayList<String>();
			likes = (List<String>) doc.get("commentedBy");
			if (likes != null) {
				for (String names : likes) {
					if(! JournalConstants.LOGGED_IN_USER.equalsIgnoreCase(names)) {
						if (cmtMap.containsKey(names)) {
							Integer temp = cmtMap.get(names);
							temp++;
							cmtMap.put(names, temp);
						} else
							cmtMap.put(names, 1);

					}// for
				}
			}
		}
		return cmtMap;
	}

}
