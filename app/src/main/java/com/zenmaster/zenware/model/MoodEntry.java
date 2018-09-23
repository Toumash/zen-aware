package com.zenmaster.zenware.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@SuppressWarnings("NullableProblems")
@Entity(tableName = "mood_entries")
public class MoodEntry {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private int mId;

  @ColumnInfo(name = "mood_score")
  private int mMoodScore;

  @ColumnInfo(name = "sleep_hours")
  private int mSleepHours;

  @ColumnInfo(name = "spacial_event")
  private String mSpecialEvent;

  public MoodEntry() {
  }

  @NonNull
  public int getmId() {
    return mId;
  }

  public MoodEntry setmId(@NonNull int mId) {
    this.mId = mId;
    return this;
  }

  public int getmMoodScore() {
    return mMoodScore;
  }

  public MoodEntry setmMoodScore(int mMoodScore) {
    this.mMoodScore = mMoodScore;
    return this;
  }

  public int getmSleepHours() {
    return mSleepHours;
  }

  public MoodEntry setmSleepHours(int mSleepHours) {
    this.mSleepHours = mSleepHours;
    return this;
  }

  public String getmSpecialEvent() {
    return mSpecialEvent;
  }

  public MoodEntry setmSpecialEvent(String mSpecialEvent) {
    this.mSpecialEvent = mSpecialEvent;
    return this;
  }
}
