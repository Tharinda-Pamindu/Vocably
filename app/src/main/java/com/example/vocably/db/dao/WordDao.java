package com.example.vocably.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vocably.db.Constraint;
import com.example.vocably.model.Word;

import java.util.List;

@Dao
public interface WordDao {
    @Query("SELECT * FROM " + Constraint.TABLE_NAME)
    LiveData<List<Word>> getAll();

    @Query("SELECT * FROM " + Constraint.TABLE_NAME + " WHERE id = :id")
    Word getById(int id);

    @Insert
    long insert(Word word);

    @Update
    void update(Word word);

    @Query("DELETE FROM " + Constraint.TABLE_NAME + " WHERE word = :word")
    void delete(String word);

    @Delete
    void deleteAll(Word... words);

}
