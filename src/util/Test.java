package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) throws ParseException {
		String startTime = "2013-12-21T18:30:00+0100";
		SimpleDateFormat incomingFormat = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ssZ");
		Date date = incomingFormat.parse(startTime);

		SimpleDateFormat outgoingFormat = new SimpleDateFormat(
				" EEEE, dd MMMM yyyy", java.util.Locale.getDefault());

		System.out.println(date);
		
		String str = "04/25/2014";
		String arr[]=str.split("/");
		System.out.println(arr[0] + "  " + arr[1] +"  "+ arr[2]);

		
	}
}
