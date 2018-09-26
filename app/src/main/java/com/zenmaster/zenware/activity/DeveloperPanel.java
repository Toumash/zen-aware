package com.zenmaster.zenware.activity;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zenmaster.zenware.R;
import com.zenmaster.zenware.service.ZenRemindersService;

public class DeveloperPanel extends AppCompatActivity implements View.OnClickListener {

  NotificationManagerCompat notificationManager;
  ZenRemindersService zenRemindersService;
  Context ctx;

  @SuppressWarnings("ConstantConditions")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_developer_panel);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    ctx = this;

    notificationManager = NotificationManagerCompat.from(this);
    zenRemindersService = new ZenRemindersService(this);
    findViewById(R.id.dev_btn_firemorning).setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.dev_btn_firemorning:
        Notification notification = zenRemindersService.createMorningNotification();
        notificationManager.notify(1, notification);
        break;
    }
  }
}
