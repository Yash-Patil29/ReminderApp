package com.example.finalyearreminderapp;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

public class AlarmReceiver extends BroadcastReceiver {

    private static final String CHANNEL_ID="SAMPLE_CHANNEL";

    @Override
    public void onReceive(Context context, Intent intent) {

        int notificationId= intent.getIntExtra("notificationId",0);
        String message =intent.getStringExtra("message");

        Intent mainIntent= new Intent(context,MainActivity.class);
        PendingIntent contentIntent= PendingIntent.getActivity(
                context,0,mainIntent,0
        );
        NotificationManager notificationManager=
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            CharSequence channel_name="My nofication";
            int importancee=NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel= new NotificationChannel(CHANNEL_ID,channel_name,importancee);
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("TITLE")
                .setContentText(message)
                .setContentIntent(contentIntent)
                .setPriority( NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);


        notificationManager.notify(notificationId,builder.build());
    }
}
