package com.greenback.app;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class DateUtility {

	public static Date parseDate(String inputDate) {

		Date outputDate = null;
		String[] possibleDateFormats = { "MMM d, yyyy", "MM/dd/yyyy" };

		try {

			outputDate = DateUtils.parseDate(inputDate, possibleDateFormats);

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return outputDate;

	}

	public static String formatDate(Date date, String requiredDateFormat) {
		SimpleDateFormat df = new SimpleDateFormat(requiredDateFormat);
		String outputDateFormatted = df.format(date);
		return outputDateFormatted;
	}

}