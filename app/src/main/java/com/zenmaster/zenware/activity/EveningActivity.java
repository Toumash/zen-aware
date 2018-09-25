package com.zenmaster.zenware.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.zenmaster.zenware.R;
import com.zenmaster.zenware.model.MoodEntry;
import com.zenmaster.zenware.viewmodel.MoodViewModel;

import java.util.Date;

public class EveningActivity extends FragmentActivity implements View.OnClickListener  {

  Context ctx;
  private MoodViewModel mWordViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_evening);

    ctx = this;
    findViewById(R.id.evening_bt_save).setOnClickListener(this);

    mWordViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);
  }

  @Override
  public void onClick(View v) {
    if (v.getId() == R.id.evening_bt_save) {
      MoodEntry mood = new MoodEntry();
      SeekBar sb = findViewById(R.id.evening_sk_mood);
      mood.setMoodScore(sb.getProgress());
      mood.setDate(new Date());
      mWordViewModel.insert(mood);
      Toast.makeText(ctx, "added new mood entry", Toast.LENGTH_SHORT).show();
      this.finish();
    }
  }

}
