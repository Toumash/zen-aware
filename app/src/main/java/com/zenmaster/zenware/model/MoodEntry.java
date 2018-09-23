package com.zenmaster.zenware.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.Date;

@SuppressWarnings({"NullableProblems", "unused"})
@Entity(tableName = "mood_entries")
public class MoodEntry {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  private int id;

  @ColumnInfo(name = "mood_score")
  private int moodScore;

  @ColumnInfo(name = "sleep_hours")
  private int sleepHours;

  @ColumnInfo(name = "spacial_event")
  private String specialEvent;

  @ColumnInfo(name = "date")
  private Date date;

  public MoodEntry() {
  }

  @NonNull
  public int getId() {
    return id;
  }

  public MoodEntry setId(@NonNull int id) {
    this.id = id;
    return this;
  }

  public int getMoodScore() {
    return moodScore;
  }

  public MoodEntry setMoodScore(int moodScore) {
    this.moodScore = moodScore;
    return this;
  }

  public int getSleepHours() {
    return sleepHours;
  }

  public MoodEntry setSleepHours(int sleepHours) {
    this.sleepHours = sleepHours;
    return this;
  }

  public String getSpecialEvent() {
    return specialEvent;
  }

  public MoodEntry setSpecialEvent(String specialEvent) {
    this.specialEvent = specialEvent;
    return this;
  }

  public Date getDate() {
    return date;
  }

  public MoodEntry setDate(Date date) {
    this.date = date;
    return this;
  }
}
