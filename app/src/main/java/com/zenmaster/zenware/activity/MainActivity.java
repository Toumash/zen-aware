package com.zenmaster.zenware.activity;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zenmaster.zenware.R;
import com.zenmaster.zenware.broadcast.NotificationPublisher;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  Context ctx;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ctx = this;
    getSupportActionBar().hide();

    findViewById(R.id.main_btn_morning).setOnClickListener(this);
    findViewById(R.id.main_btn_evening).setOnClickListener(this);
    findViewById(R.id.main_btn_afternoon).setOnClickListener(this);
    findViewById(R.id.main_btn_entries).setOnClickListener(this);

    CreateChannelId();
    scheduleNotification(getNotification("Morning", "Hello sir"), 5000);
  }

  private void CreateChannelId() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

// The id of the channel.
      String id = NotificationPublisher.CHANNEL_REMINDERS;

// The user-visible name of the channel.
      CharSequence name = getString(R.string.channel_name);

// The user-visible description of the channel.
      String description = getString(R.string.channel_description);

      int importance = NotificationManager.IMPORTANCE_LOW;
      NotificationChannel mChannel = new NotificationChannel(id, name, importance);

// Configure the notification channel.
      mChannel.setDescription(description);

      mChannel.enableLights(true);
// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
      mChannel.setLightColor(Color.GREEN);

      mChannel.enableVibration(true);
      mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});

      if (mNotificationManager != null) {
        mNotificationManager.createNotificationChannel(mChannel);
      }
    }
  }

  private void scheduleNotification(Notification notification, int delay) {

    Intent notificationIntent = new Intent(this, NotificationPublisher.class);
    notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
    notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

    long futureInMillis = SystemClock.elapsedRealtime() + delay;
    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
    alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);
  }

  private Notification getNotification(String title, String description) {
    Notification.Builder builder = new Notification.Builder(this);
    builder.setContentTitle(title);
    builder.setContentText(description);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      builder.setChannelId(NotificationPublisher.CHANNEL_REMINDERS);
    }
    builder.setSmallIcon(R.drawable.ic_launcher_foreground);
    return builder.build();
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.main_btn_morning) {
      Intent intent = new Intent(ctx, MorningActivity.class);
      startActivity(intent);
    } else if (v.getId() == R.id.main_btn_afternoon) {
      Intent intent = new Intent(ctx, NoonActivity.class);
      startActivity(intent);
    } else if (v.getId() == R.id.main_btn_evening) {
      Intent intent = new Intent(ctx, EveningActivity.class);
      startActivity(intent);
    } else if (v.getId() == R.id.main_btn_entries) {
      startActivity(new Intent(ctx, MoodEntriesActivity.class));
    }
  }
}
