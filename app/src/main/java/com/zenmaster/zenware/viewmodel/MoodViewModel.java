package com.zenmaster.zenware.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.zenmaster.zenware.dal.MoodRepo;
import com.zenmaster.zenware.model.MoodEntry;

import java.util.List;

@SuppressWarnings("unused")
public class MoodViewModel extends AndroidViewModel {

  private MoodRepo mRepository;

  private LiveData<List<MoodEntry>> allEntries;

  public MoodViewModel(Application application) {
    super(application);
    mRepository = new MoodRepo(application);
    allEntries = mRepository.getAllEntries();
  }

  public LiveData<List<MoodEntry>> getAllEntries() {
    return allEntries;
  }

  public void insert(MoodEntry moodEntry) {
    mRepository.insert(moodEntry);
  }
}
