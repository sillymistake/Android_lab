package com.example.administrator.lab3_code;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.administrator.lab3_code.R;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class ShopWidget extends AppWidgetProvider {

    final static String STATICACTION_WIDGET = "static_widget";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        CharSequence widgetText = context.getString(R.string.no_message);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.shop_widget);
        views.setTextViewText(R.id.widget_text, widgetText);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.shop_widget);
        Intent i = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget, pi);
        ComponentName me = new ComponentName(context, ShopWidget.class);
        appWidgetManager.updateAppWidget(me, views);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context,intent);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        if(intent.getAction().equals(STATICACTION_WIDGET)) {
            Bundle bundle = intent.getExtras();
            final String name = bundle.getString("name");
            final String price = bundle.getString("price");
            final String type = bundle.getString("type");
            final String info = bundle.getString("info");

            RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.shop_widget);
            updateViews.setTextViewText(R.id.widget_text, name+"仅售"+price+"!!!");
            switch (name) {
                case "Enchated Forest":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.enchatedforest);
                    break;
                case "Arla Milk":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.arla);
                    break;
                case "Devondale Milk":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.devondale);
                    break;
                case "Kindle Oasis":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.kindle);
                    break;
                case "waitrose 早餐麦片":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.waitrose);
                    break;
                case "Mcvitie's 饼干":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.mcvitie);
                    break;
                case "Ferrero Rocher":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.ferrero);
                    break;
                case "Maltesers":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.maltesers);
                    break;
                case "Lindt":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.lindt);
                    break;
                case "Borggreve":
                    updateViews.setImageViewResource(R.id.widget_pic, R.drawable.borggreve);
                    break;
                default:
                    break;
            }
            Intent mIntent = new Intent(context,detail.class);
            mIntent.putExtra("name",name);
            mIntent.putExtra("price",price);
            mIntent.putExtra("type",type);
            mIntent.putExtra("info",info);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context,0,mIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            updateViews.setOnClickPendingIntent(R.id.widget,mPendingIntent);
            ComponentName me = new ComponentName(context,ShopWidget.class);
            appWidgetManager.updateAppWidget(me,updateViews);
        }
    }
}

