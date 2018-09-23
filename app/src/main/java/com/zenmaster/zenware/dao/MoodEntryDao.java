package com.zenmaster.zenware.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.zenmaster.zenware.model.MoodEntry;

import java.util.List;

@Dao
public interface MoodEntryDao {

  @Insert
  void insert(MoodEntry moodEntry);

  @Query("DELETE FROM mood_entries")
  void deleteAll();

  @Query("SELECT * from mood_entries ORDER BY id")
  List<MoodEntry> getAllMoodEntries();
}
