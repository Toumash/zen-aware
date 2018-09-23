package com.zenmaster.zenware;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

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
            Intent intent = new Intent(ctx, MainActivity.class);
            Toast.makeText(ctx,
                    "change the MainActivity.class to your desire to make it work", Toast.LENGTH_LONG)
                    .show();
            startActivity(intent);
        }
    }
}
