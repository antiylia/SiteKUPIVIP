package by.htp.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TransformData {
	
	public static String transformDateIntoString () {
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd  hh'H'-mm'm'-ss'sec'-SS'millisec'");
		String dateStr = sf.format(date);
		return dateStr;
	}

}
