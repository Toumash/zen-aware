package com.zenmaster.zenware.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.zenmaster.zenware.R;
import com.zenmaster.zenware.model.MoodEntry;
import com.zenmaster.zenware.model.TimeOfDay;
import com.zenmaster.zenware.viewmodel.MoodViewModel;

import java.util.Date;
import java.util.List;

public class MorningActivity extends FragmentActivity implements View.OnClickListener {

  Context ctx;
  private MoodViewModel mWordViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.morning_activity);
    ctx = this;
    findViewById(R.id.morning_save).setOnClickListener(this);

    mWordViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.morning_save) {
      MoodEntry mood = new MoodEntry();
      SeekBar sb = findViewById(R.id.morning_sb_mood);
      mood.setMoodScore(sb.getProgress());
      mood.setDate(new Date());
      mood.setTimeOfDay(TimeOfDay.Morning.toString());
      mWordViewModel.insert(mood);
      Toast.makeText(ctx, "added new mood entry", Toast.LENGTH_SHORT).show();
      this.finish();
    }
  }
}
