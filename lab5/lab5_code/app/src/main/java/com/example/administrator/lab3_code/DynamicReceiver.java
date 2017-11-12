package com.example.administrator.lab3_code;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by Administrator on 2017/10/29.
 */

public class DynamicReceiver extends BroadcastReceiver {
    private String DYNAMICACTION = "dynamic";
    private String DYNAMICACTION_WIDGET = "dynamic_widget";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(DYNAMICACTION)) {
            Bundle bundle = intent.getExtras();
            final String name = bundle.getString("name");
            Bitmap bm = null;
            switch (name) {
                case "Enchated Forest":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.enchatedforest);
                    break;
                case "Arla Milk":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.arla);
                    break;
                case "Devondale Milk":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.devondale);
                    break;
                case "Kindle Oasis":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.kindle);
                    break;
                case "waitrose 早餐麦片":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.waitrose);
                    break;
                case "Mcvitie's 饼干":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.mcvitie);
                    break;
                case "Ferrero Rocher":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.ferrero);
                    break;
                case "Maltesers":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.maltesers);
                    break;
                case "Lindt":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.lindt);
                    break;
                case "Borggreve":
                    bm = BitmapFactory.decodeResource(context.getResources(), R.drawable.borggreve);
                    break;
                default:
                    break;
            }

            NotificationManager manager = (NotificationManager)
                    context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("马上下单")
                    .setContentText(name+"已添加到购物车")
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bm)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);

            Intent mIntent = new Intent(context, MainActivity.class);
            mIntent.putExtra("action","sc_list");
            PendingIntent mPendingIntent = PendingIntent.getActivity(context,0,mIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);
            Notification notification = builder.build();
            manager.notify(0, notification);

        }

        if(intent.getAction().equals(DYNAMICACTION_WIDGET)) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            Bundle bundle = intent.getExtras();
            final String name = bundle.getString("name");
            RemoteViews updateViews = new RemoteViews(context.getPackageName(),R.layout.shop_widget);
            updateViews.setTextViewText(R.id.widget_text, name+"已添加到购物车");
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
            Intent mIntent = new Intent(context, MainActivity.class);
            mIntent.putExtra("action","sc_list");
            PendingIntent mPendingIntent = PendingIntent.getActivity(context,0,mIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            updateViews.setOnClickPendingIntent(R.id.widget,mPendingIntent);
            ComponentName me = new ComponentName(context,ShopWidget.class);
            appWidgetManager.updateAppWidget(me,updateViews);
        }
    }
}
