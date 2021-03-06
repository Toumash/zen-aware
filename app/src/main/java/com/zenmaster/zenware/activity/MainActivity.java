package com.zenmaster.zenware.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.zenmaster.zenware.R;
import com.zenmaster.zenware.service.ZenRemindersService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
  Context ctx;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ctx = this;

    ActionBar actionBar = getSupportActionBar();
    if (actionBar != null)
      actionBar.hide();

    findViewById(R.id.main_btn_morning).setOnClickListener(this);
    findViewById(R.id.main_btn_evening).setOnClickListener(this);
    findViewById(R.id.main_btn_afternoon).setOnClickListener(this);
    findViewById(R.id.main_btn_entries).setOnClickListener(this);
    findViewById(R.id.main_btn_developermenu).setOnClickListener(this);

    new ZenRemindersService(this).ScheduleAllNotifications();
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
    } else if (v.getId() == R.id.main_btn_developermenu) {
      startActivity(new Intent(ctx, DeveloperPanel.class));
    }
  }
}
