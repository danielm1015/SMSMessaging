/*
 * Copyright (c) 2016. All Rights Reserved.
 */

package com.rabor.smsmessaging;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.RemoteViews;


/**
 * Implementation of App Widget functionality.
 */
public class Sms extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int currentWidgetId = appWidgetIds[i];
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("sms:"));
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            // Construct the RemoteViews object
            RemoteViews views = new RemoteViews(context.getPackageName(),
                    R.layout.sms);

            Intent launchAppIntent = new Intent(context, MainActivity.class);
            PendingIntent launchPendingIntent = PendingIntent.getActivity(context,
                    0, launchAppIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            views.setOnClickPendingIntent(R.id.msgImageView, launchPendingIntent);

            ComponentName appWidget = new ComponentName(context, Sms.class);
            // Instruct the widget manager to update the widget
            appWidgetManager.updateAppWidget(appWidget, views);
        }
    }
}

