package com.mobile.interestgroup.ecalendar.ui.widget;

import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class WeekTitleActivity extends RemoteViewsService {

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		return new WeekTitleRemoteViewsFactory(this, intent);
	}
	
	private class WeekTitleRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

		private Context mContext;
		private String[] daysOfWeekTitle=new String[7];
		
		public WeekTitleRemoteViewsFactory(Context context, Intent intent) {
			mContext = context;
			// mAppWidgetId =
			// intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
			// AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return this.daysOfWeekTitle.length;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public RemoteViews getLoadingView() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RemoteViews getViewAt(int position) {
			System.out.println("this.daysOfWeekTitle[position]="+this.daysOfWeekTitle[position]);
			RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.week_title);
			rv.setTextViewText(R.id.week_day_desc, this.daysOfWeekTitle[position]);
			return rv;
		}

		@Override
		public int getViewTypeCount() {
			// TODO Auto-generated method stub
			return 1;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onCreate() {
			init();
			
		}

		@Override
		public void onDataSetChanged() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onDestroy() {
			// TODO Auto-generated method stub
			
		}
		
		public void init()
		{
			Calendar calendar = Calendar.getInstance();
			int firstDayOfWeek = calendar.getFirstDayOfWeek()-1;
			//TODO,should not hard code day description here.
			String[] daysDescInWeek = {"Sun","Mon","Tue","Wen","Thu","Fri","Sat"};
			for(int i=0; i<7; i++)
			{
				daysOfWeekTitle[i] = daysDescInWeek[(i+firstDayOfWeek)%7];
			}
		}
		
	}

}
