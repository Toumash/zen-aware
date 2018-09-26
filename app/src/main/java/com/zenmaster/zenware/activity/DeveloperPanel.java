package com.zenmaster.zenware.activity;

import android.os.Bundle;
import android.app.Activity;

import com.zenmaster.zenware.R;

public class DeveloperPanel extends Activity {

  @SuppressWarnings("ConstantConditions")
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_developer_panel);
    getActionBar().setDisplayHomeAsUpEnabled(true);
  }

}
