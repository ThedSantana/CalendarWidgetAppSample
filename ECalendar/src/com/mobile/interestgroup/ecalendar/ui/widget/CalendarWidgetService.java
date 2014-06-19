package com.mobile.interestgroup.ecalendar.ui.widget;

import com.mobile.interestgroup.ecalendar.data.CalendarDayDetails;
import com.mobile.interestgroup.ecalendar.data.CalendarDetails;
import com.mobile.interestgroup.ecalendar.ui.widget.R.color;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

public class CalendarWidgetService extends RemoteViewsService {

	private static final String TAG = "eCalendar";

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		Log.d(TAG, "GridWidgetService");
		return new CalendarWidgetRemoteViewsFactory(this, intent);
	}

	private class CalendarWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
		private Context mContext;
		// private int mAppWidgetId;
		CalendarDetails calendarDetails;

		public CalendarWidgetRemoteViewsFactory(Context context, Intent intent) {
			mContext = context;
			// mAppWidgetId =
			// intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
			// AppWidgetManager.INVALID_APPWIDGET_ID);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return CalendarDetails.CELL_SIZE;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public RemoteViews getLoadingView() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public RemoteViews getViewAt(int position) {

			RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.calendar_grid_view);

			// rv.setImageViewResource(R.id.itemImage,
			// ((Integer)map.get(IMAGE_ITEM)).intValue());
			Log.d(TAG, "getViewAt position="+position);
			CalendarDayDetails dayDetails = calendarDetails.getCalendarDayDetailsAtPosition(position);
			Log.d(TAG, "getViewAt day="+dayDetails.getMajorDay());
			rv.setTextViewText(R.id.major_date, dayDetails.getMajorDay());
			
			if(dayDetails.getMajorDate().equals(calendarDetails.getToday()))
			{
				rv.setTextColor(R.id.major_date, Color.RED);
				//rv.setTextViewTextSize(R.id.major_date, COMPLEX_UNIT_SP, size);
				
			}

			/*
			 * Intent fillInIntent = new Intent();
			 * fillInIntent.putExtra(CalendarWidgetProvider
			 * .COLLECTION_VIEW_EXTRA, position);
			 * rv.setOnClickFillInIntent(R.id.itemLayout, fillInIntent);
			 */

			return rv;
		}

		@Override
		public int getViewTypeCount() {
			return 1;
		}

		@Override
		public boolean hasStableIds() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void onCreate() {
			Log.d(TAG, "onCreate");
			initDayGridViewData();

		}

		@Override
		public void onDataSetChanged() {
			// TODO Auto-generated method stub

		}

		@Override
		public void onDestroy() {
			//Log.d(TAG, "onDestroy");
			//calendarDetails = null;
		}

		private void initDayGridViewData() {
			calendarDetails = new CalendarDetails();
			calendarDetails.init();
		}

	}

}
