package com.example.administrator.lab3_code;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.widget.Toast;

/**
 * Created by Administrator on 2017/10/29.
 */

public class StaticReceiver extends BroadcastReceiver {

    private String STATICACTION = "static";

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(STATICACTION)) {
            Bundle bundle = intent.getExtras();
            final String name = bundle.getString("name");
            final String price = bundle.getString("price");
            final String type = bundle.getString("type");
            final String info = bundle.getString("info");
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

            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
            builder.setContentTitle("新商品热卖")
                    .setContentText(name+"仅售"+price+"!!!")
                    .setTicker("您有一条新消息")
                    .setLargeIcon(bm)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setAutoCancel(true);

            Intent mIntent = new Intent(context, detail.class);
            mIntent.putExtra("name",name);
            mIntent.putExtra("price",price);
            mIntent.putExtra("type",type);
            mIntent.putExtra("info",info);
            PendingIntent mPendingIntent = PendingIntent.getActivity(context,0,mIntent,PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(mPendingIntent);

            Notification notification = builder.build();
            manager.notify(0, notification);

        }
    }


}
