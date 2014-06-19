package com.mobile.interestgroup.ecalendar.data;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import android.util.Log;

public class CalendarDetails {
	private static final int ROWS_SIZE = 6;
	private static final int COLS_SIZE = 7;
	public static final int CELL_SIZE=ROWS_SIZE*COLS_SIZE;
	/*
	 * private static final int CELLS_SIZE = ROWS_SIZE * COLS_SIZE; private
	 * String[][] gregoianDates = new String[ROWS_SIZE][COLS_SIZE]; private
	 * String[][] minorDates = new String[ROWS_SIZE][COLS_SIZE]; private
	 * String[][] holidayMark = new String[ROWS_SIZE][COLS_SIZE]; private
	 * String[][] datingMark = new String[ROWS_SIZE][COLS_SIZE];
	 * 
	 * private static final String GREG_DATE_ITEM = "GREG_DATE_ITEM"; private
	 * static final String MINOR_DATE_ITEM = "MINOR_DATE_ITEM"; private static
	 * final String HOLIDAY_MARK_ITEM = "HOLIDAY_MARK_ITEM"; private static
	 * final String DATING_MARK_ITEM = "DATING_MARK_ITEM";
	 */
	private Date today;

	private CalendarDayDetails[][] dates = new CalendarDayDetails[ROWS_SIZE][COLS_SIZE];

	public void init() {
		today = new Date();
		initCalendar(today);
	}

	public void refresh(String yearDate) {
		if (StringUtils.isNotEmpty(yearDate)) {
			yearDate = StringUtils.replace(yearDate, " ", "");
			if (StringUtils.length(yearDate) > 4 // yyyyM or yyyyMM
					&& NumberUtils.isNumber(yearDate)) {

			}
		}
	}

	public void initCalendar(final Date date) {
		Calendar today = Calendar.getInstance();
		today.setTime(date);
		int dayOfWeek = today.get(Calendar.DAY_OF_WEEK);
		int weekOfMonth = today.get(Calendar.WEEK_OF_MONTH);
		
		int todayOffsetInMonthCalendar = dayOfWeek + (weekOfMonth - 1) * COLS_SIZE - 1;
		
		Calendar firstDayInCalendar = (Calendar) today.clone();
		firstDayInCalendar.add(Calendar.DAY_OF_YEAR, -todayOffsetInMonthCalendar-1);
		
		Log.d("eCalendar","todayOffsetInMonthCalendar= "+todayOffsetInMonthCalendar );
		for (int row = 0; row < ROWS_SIZE; row++) {
			for (int col = 0; col < COLS_SIZE; col++) {
				CalendarDayDetails dayDetail = dates[row][col];
				if (dayDetail == null) {
					dayDetail = new CalendarDayDetails();
					dates[row][col]=dayDetail;
				}
				
				firstDayInCalendar.add(Calendar.DAY_OF_YEAR, 1);
				Date majorDate = new Date(firstDayInCalendar.getTimeInMillis());
				dayDetail.setMajorDate(majorDate);
				Log.d("eCalendar","getCalendarDayDetailsAtPosition"+dayDetail.getMajorDate());
			}
		}

	}
	
	public CalendarDayDetails getCalendarDayDetailsAtPosition(int rowIndex, int columnIndex)
	{
		if(rowIndex<0 || rowIndex>=ROWS_SIZE || columnIndex<0 || columnIndex>=COLS_SIZE)
		{
			//TODO, throws exception
		}
		return dates[rowIndex][columnIndex];
	}
	
	public CalendarDayDetails getCalendarDayDetailsAtPosition(int cellIndex)
	{
		if(cellIndex<0 || cellIndex>=CELL_SIZE)
		{
			//TODO, throws exception
		}
		int rowIndex = cellIndex/COLS_SIZE;
		int columnIndex = cellIndex-rowIndex*COLS_SIZE;
		Log.d("eCalendar","getCalendarDayDetailsAtPosition"+rowIndex);
		Log.d("eCalendar","getCalendarDayDetailsAtPosition"+columnIndex);
		return this.getCalendarDayDetailsAtPosition(rowIndex, columnIndex);
	}
	
	public Date getToday()
	{
		return this.today;
	}

}
