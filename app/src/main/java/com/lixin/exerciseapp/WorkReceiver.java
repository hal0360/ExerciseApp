package com.lixin.exerciseapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

public class WorkReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent dayActivity = null;
        boolean toSend = true;
        int weekDay = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        switch (weekDay) {
            case Calendar.MONDAY:
                dayActivity = new Intent(context, MondayActivity.class);
                break;
            case Calendar.TUESDAY:
                dayActivity = new Intent(context, TuesdayActivity.class);
                break;
            case Calendar.WEDNESDAY:
                dayActivity = new Intent(context, WednesdayActivity.class);
                break;
            case Calendar.THURSDAY:
                dayActivity = new Intent(context, ThursdayActivity.class);
                break;
            case Calendar.FRIDAY:
                dayActivity = new Intent(context, FridayActivity.class);
                break;
            default:
                toSend = false;
        }

        if (toSend && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            dayActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, dayActivity, PendingIntent.FLAG_IMMUTABLE | PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "workID")
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Time for your weekday workout")
                    .setContentText("Click this notification for your workout detail")
                    .setAutoCancel(true)
                    .setDefaults(NotificationCompat.DEFAULT_ALL)
                    .setPriority(NotificationCompat.PRIORITY_HIGH)
                    .setContentIntent(pendingIntent);

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            notificationManagerCompat.notify(767, builder.build());
        }


    }
}