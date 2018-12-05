package com.zenmaster.zenware.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

@SuppressWarnings({"NullableProblems", "unused"})
@Entity(tableName = "mood_entries")
public class MoodEntry {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    @ColumnInfo(name = "time_of_day")
    private String timeOfDay;

    @ColumnInfo(name = "mood_score")
    private int moodScore;

    @ColumnInfo(name = "sleep_hours")
    private int sleepHours;

    @ColumnInfo(name = "spacial_event")
    private String specialEvent;

    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "well_eaten")
    private Boolean wellEaten;

    public Boolean getWellEaten() {
        return wellEaten;
    }

    public void setWellEaten(Boolean wellEaten) {
        this.wellEaten = wellEaten;
    }

    public MoodEntry() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getMoodScore() {
        return moodScore;
    }

    public void setMoodScore(int moodScore) {
        this.moodScore = moodScore;
    }

    public int getSleepHours() {
        return sleepHours;
    }

    public void setSleepHours(int sleepHours) {
        this.sleepHours = sleepHours;
    }

    public String getSpecialEvent() {
        return specialEvent;
    }

    public void setSpecialEvent(String specialEvent) {
        this.specialEvent = specialEvent;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}