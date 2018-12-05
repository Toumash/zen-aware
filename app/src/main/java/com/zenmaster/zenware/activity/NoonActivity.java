package com.zenmaster.zenware.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.Toast;

import com.xw.repo.BubbleSeekBar;
import com.zenmaster.zenware.R;
import com.zenmaster.zenware.model.MoodEntry;
import com.zenmaster.zenware.model.TimeOfDay;
import com.zenmaster.zenware.viewmodel.MoodViewModel;

import java.util.Date;

public class NoonActivity extends FragmentActivity implements View.OnClickListener  {

    Context ctx;
    private MoodViewModel mWordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.noon_activity);

        ctx = this;
        findViewById(R.id.noon_bt_save).setOnClickListener(this);

        mWordViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.noon_bt_save) {

            MoodEntry mood = new MoodEntry();
            TextInputLayout event_name = findViewById(R.id.input);
            mood.setSpecialEvent(event_name.getEditText().getText().toString());
            BubbleSeekBar sb = findViewById(R.id.noon_sk_mood);
            mood.setMoodScore(sb.getProgress());
            mood.setDate(new Date());
            mood.setTimeOfDay(TimeOfDay.Noon.toString());
            mWordViewModel.insert(mood);
            Toast.makeText(ctx, "added new mood entry", Toast.LENGTH_SHORT).show();
            this.finish();
        }
    }
}
