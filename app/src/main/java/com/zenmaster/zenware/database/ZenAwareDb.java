package com.zenmaster.zenware.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.zenmaster.zenware.Converters;
import com.zenmaster.zenware.dao.MoodEntryDao;
import com.zenmaster.zenware.model.MoodEntry;

import java.util.Date;

@SuppressWarnings("unused")
@Database(entities = {MoodEntry.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class ZenAwareDb extends RoomDatabase {
  private static volatile ZenAwareDb INSTANCE;
  private static RoomDatabase.Callback sRoomDatabaseCallback =
    new RoomDatabase.Callback() {

      @Override
      public void onOpen(@NonNull SupportSQLiteDatabase db) {
        super.onOpen(db);
        new PopulateDbAsync(INSTANCE).execute();
      }
    };

  public static ZenAwareDb getDatabase(final Context context) {
    if (INSTANCE == null) {
      synchronized (ZenAwareDb.class) {
        if (INSTANCE == null) {
          INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
            ZenAwareDb.class, "zenaware_database")
            .addCallback(sRoomDatabaseCallback)
            .build();
        }
      }
    }
    return INSTANCE;
  }

  public abstract MoodEntryDao moodDao();

  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final MoodEntryDao mDao;

    PopulateDbAsync(ZenAwareDb db) {
      mDao = db.moodDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
      mDao.deleteAll();
      MoodEntry entry = new MoodEntry();
      entry.setId(0);
      Date d = new Date();
      d.setYear(2007);
      entry.setDate(d);
      mDao.insert(entry);
      mDao.insert(entry);
      return null;
    }
  }
}
