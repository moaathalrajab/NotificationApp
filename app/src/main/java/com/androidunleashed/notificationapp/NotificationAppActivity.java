package com.androidunleashed.notificationapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.app.Notification;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.RemoteViews;

public class NotificationAppActivity extends Activity {

    private static int id = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_app);
        Button createButton = (Button) findViewById(R.id.createbutton);
        createButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View arg0) {
                Intent intent = new Intent(getBaseContext(), TargetActivity.class);
                PendingIntent pendIntent = PendingIntent.getActivity(getBaseContext(), 0, intent, 0);
                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE); 
                Notification notification = new Notification();

//                // for default notification
//                NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())
//                    .setSmallIcon(R.drawable.notification_icon)
//                    .setAutoCancel(true)
//                    .setTicker("There is a new notification")
//                    .setWhen(System.currentTimeMillis())
//                    .setContentTitle("New E-mail")
//                    .setContentText("You have one unread message")
//                    .setContentIntent(pendIntent);
//                builder.setNumber(id);
//                notification = builder.build();

                // for custom notification layout
                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.custom_notification);
                contentView.setImageViewResource(R.id.notification_image, R.drawable.notification_icon);
                contentView.setTextViewText(R.id.notification_title, "My custom notification title");
                contentView.setTextViewText(R.id.notification_text, "My custom notification text");
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext())
                    .setSmallIcon(R.drawable.notification_icon)
                    .setAutoCancel(true)
                    .setTicker("There is a new notification")
                    .setWhen(System.currentTimeMillis())
                    .setContent(contentView)
                    .setContentIntent(pendIntent);
                notification = builder.build();

                notificationManager.notify(id++, notification);
                notificationManager.cancel((id - 2));
            }
        });
    }
}
