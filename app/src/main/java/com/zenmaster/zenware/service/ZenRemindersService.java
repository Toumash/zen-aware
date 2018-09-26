package com.zenmaster.zenware.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;

import com.zenmaster.zenware.R;
import com.zenmaster.zenware.activity.MorningActivity;
import com.zenmaster.zenware.broadcast.NotificationPublisher;

public class ZenRemindersService {

  Context ctx;


  public ZenRemindersService(Context ctx) {
    this.ctx = ctx;
  }

  public void DisplayMorningNotification() {
    CreateChannelId();
    Notification notification = getNotification("Poranny nastrój", "Jak ci minął ranek?", MorningActivity.class);
    scheduleNotification(notification, 5000);
  }

  private void CreateChannelId() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
      CharSequence name = ctx.getString(R.string.channel_name);
      String description = ctx.getString(R.string.channel_description);
      int importance = NotificationManager.IMPORTANCE_LOW;
      NotificationChannel mChannel = new NotificationChannel(NotificationPublisher.CHANNEL_REMINDERS, name, importance);

      mChannel.setDescription(description);
      mChannel.enableLights(true);
      mChannel.setLightColor(Color.GREEN);
      mChannel.enableVibration(true);
      mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
      if (mNotificationManager != null) {
        mNotificationManager.createNotificationChannel(mChannel);
      }
    }
  }

  private void scheduleNotification(Notification notification, int delay) {

    Intent notificationIntent = new Intent(ctx, NotificationPublisher.class);
    notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
    notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(ctx, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    long futureInMillis = SystemClock.elapsedRealtime() + delay;
    AlarmManager alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
  }

  private Notification getNotification(String title, String description, Class<?> activityToRun) {
    Notification.Builder builder = new Notification.Builder(ctx);
    builder.setContentTitle(title);
    builder.setContentText(description);

    Intent notifyIntent = new Intent(ctx, activityToRun);
    notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
      | Intent.FLAG_ACTIVITY_CLEAR_TASK);
    PendingIntent notifyPendingIntent = PendingIntent.getActivity(
      ctx, 0, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT
    );
    builder.setContentIntent(notifyPendingIntent);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      builder.setChannelId(NotificationPublisher.CHANNEL_REMINDERS);
    }
    builder.setSmallIcon(R.drawable.ic_launcher_foreground);
    return builder.build();
  }
}
