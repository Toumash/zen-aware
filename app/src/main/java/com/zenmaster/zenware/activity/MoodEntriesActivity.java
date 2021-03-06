package com.zenmaster.zenware.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zenmaster.zenware.R;
import com.zenmaster.zenware.model.MoodEntry;
import com.zenmaster.zenware.viewmodel.MoodViewModel;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class MoodEntriesActivity extends FragmentActivity {
  private MoodViewModel mWordViewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mood);
    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    final WordListAdapter adapter = new WordListAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    mWordViewModel = ViewModelProviders.of(this).get(MoodViewModel.class);
    mWordViewModel.getAllEntries().observe(this, new Observer<List<MoodEntry>>() {
      @Override
      public void onChanged(@Nullable final List<MoodEntry> words) {
        // Update the cached copy of the words in the adapter.
        adapter.setWords(words);
      }
    });
  }

}

class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

  private final LayoutInflater mInflater;
  private List<MoodEntry> mWords; // Cached copy of words

  WordListAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
  }

  @Override
  public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.recyclerview_mood_entry, parent, false);
    return new WordViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(WordViewHolder holder, int position) {
    if (mWords != null) {
      MoodEntry current = mWords.get(position);
      SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.ENGLISH);
      if (current.getDate() != null)
        holder.dateView.setText(dt.format(current.getDate()));
      else
        holder.dateView.setText("Invalid date");

      holder.timeOfDay.setText(String.valueOf(current.getTimeOfDay()));
      holder.sleepView.setText(String.valueOf(current.getSleepHours()));
      holder.scoreView.setText(String.valueOf(current.getMoodScore()));
      holder.eventView.setText(String.valueOf(current.getSpecialEvent()));
      holder.wellEaten.setText(String.valueOf(current.getSpecialEvent()));
    } else {
      // Covers the case of data not being ready yet.
      holder.dateView.setText("No date");
    }
  }

  void setWords(List<MoodEntry> words) {
    mWords = words;
    notifyDataSetChanged();
  }

  // getItemCount() is called many times, and when it is first called,
  // mWords has not been updated (means initially, it's null, and we can't return null).
  @Override
  public int getItemCount() {
    if (mWords != null)
      return mWords.size();
    else return 0;
  }

  class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView scoreView;
    private final TextView dateView;
    private final TextView sleepView;
    private final TextView eventView;
    private final TextView wellEaten;
    private final TextView timeOfDay;

    private WordViewHolder(View itemView) {
      super(itemView);
      dateView = itemView.findViewById(R.id.mood_date);
      scoreView = itemView.findViewById(R.id.mood_score);
      sleepView = itemView.findViewById(R.id.mood_sleep);
      eventView = itemView.findViewById(R.id.mood_event);
      wellEaten = itemView.findViewById(R.id.mood_eat);
      timeOfDay = itemView.findViewById(R.id.time_of_day);
    }
  }
}
