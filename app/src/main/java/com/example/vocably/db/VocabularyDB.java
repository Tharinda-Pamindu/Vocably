package com.example.vocably.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.vocably.db.dao.WordDao;
import com.example.vocably.model.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class VocabularyDB extends RoomDatabase {
    private static VocabularyDB vocabularyDB;

    public abstract WordDao wordDao();

    public static synchronized VocabularyDB getInstance(Context ctx) {
        if (vocabularyDB == null)
            vocabularyDB = Room.databaseBuilder(ctx.getApplicationContext(), VocabularyDB.class, Constraint.DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        return vocabularyDB;
    }
}
