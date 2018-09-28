package com.zenmaster.zenware.activity;

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
    findViewById(R.id.dev_btn_firenoon).setOnClickListener(this);
    findViewById(R.id.dev_btn_fireevening).setOnClickListener(this);
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.dev_btn_firemorning:
        Notification morningNotify = zenRemindersService.createMorningNotification();
        notificationManager.notify(1, morningNotify);
        break;
      case R.id.dev_btn_firenoon:
        Notification noonNotify = zenRemindersService.createNoonNotification();
        notificationManager.notify(1, noonNotify);
        break;
      case R.id.dev_btn_fireevening:
        Notification eveningNotify = zenRemindersService.createEveningNotification();
        notificationManager.notify(1, eveningNotify);
        break;
    }
  }
}
