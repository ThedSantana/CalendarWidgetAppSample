package com.mobile.interestgroup.ecalendar.data;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CalendarDayDetails {
	private Date majorDate;
	private Date minorDate; // TODO
	private List<String> holidayMark;
	boolean hasEvent;

	public Date getMajorDate() {
		return majorDate;
	}

	public void setMajorDate(Date majorDate) {
		this.majorDate = majorDate;
	}

	public Date getMinorDate() {
		return minorDate;
	}

	public void setMinorDate(Date minorDate) {
		this.minorDate = minorDate;
	}

	public List<String> getHolidayMark() {
		return holidayMark;
	}

	public void setHolidayMark(List<String> holidayMark) {
		this.holidayMark = holidayMark;
	}

	public boolean isHasEvent() {
		return hasEvent;
	}

	public void setHasEvent(boolean hasEvent) {
		this.hasEvent = hasEvent;
	}

	public String getMajorDay() {
		String majorDay = "";
		if (this.majorDate != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
			String dateStr = sdf.format(this.majorDate);
			majorDay = dateStr.substring(6);
			if (majorDay.startsWith("0")) {
				majorDay = majorDay.substring(1);
			}
		}
		return majorDay;
	}

}
