package com.zenmaster.zenware.dal;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.zenmaster.zenware.dao.MoodEntryDao;
import com.zenmaster.zenware.database.ZenAwareDb;
import com.zenmaster.zenware.model.MoodEntry;

import java.util.List;

@SuppressWarnings("unused")
public class MoodRepo {

  private MoodEntryDao moodDao;
  private LiveData<List<MoodEntry>> allMoodEntries;

  public MoodRepo(Application application) {
    ZenAwareDb db = ZenAwareDb.getDatabase(application);
    moodDao = db.moodDao();
    allMoodEntries = moodDao.getAllMoodEntries();
  }

  public LiveData<List<MoodEntry>> getAllEntries() {
    return allMoodEntries;
  }


  public void insert (MoodEntry word) {
    new insertAsyncTask(moodDao).execute(word);
  }

  private static class insertAsyncTask extends AsyncTask<MoodEntry, Void, Void> {

    private MoodEntryDao mAsyncTaskDao;

    insertAsyncTask(MoodEntryDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final MoodEntry... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }
}
