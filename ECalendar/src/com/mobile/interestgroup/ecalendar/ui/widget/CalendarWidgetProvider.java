package com.mobile.interestgroup.ecalendar.ui.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class CalendarWidgetProvider extends AppWidgetProvider {

	public static final String BT_REFRESH_ACTION = "com.mobile.interestgroup.ecalendar.ui.widget.BT_REFRESH_ACTION";

	// public static final String COLLECTION_VIEW_ACTION =
	// "com.mobile.interestgroup.ecalendar.ui.widget.COLLECTION_VIEW_ACTION";
	public static final String COLLECTION_VIEW_EXTRA = "com.mobile.interestgroup.ecalendar.ui.widget.COLLECTION_VIEW_EXTRA";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		for (int appWidgetId : appWidgetIds) {
			
			RemoteViews rv = new RemoteViews(context.getPackageName(), R.layout.calendar_widget_provider);

			//set days grid view
			Intent serviceIntent = new Intent(context, CalendarWidgetService.class);
			rv.setRemoteAdapter(R.id.days_grid_view, serviceIntent);
			appWidgetManager.updateAppWidget(appWidgetId, rv);
			
			
			RemoteViews weekDayDesc = new RemoteViews(context.getPackageName(), R.layout.calendar_widget_provider);
			Intent weekTitle = new Intent(context, WeekTitleActivity.class);
			weekDayDesc.setRemoteAdapter(R.id.week_grid_view, weekTitle);
			appWidgetManager.updateAppWidget(appWidgetId, weekDayDesc);
			
			
		}
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
}
