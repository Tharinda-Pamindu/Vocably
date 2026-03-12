package com.example.vocably.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.vocably.db.Constraint;

@Entity(tableName = Constraint.TABLE_NAME)
public class Word {
    @PrimaryKey(autoGenerate = true)
    private long id;
    private String word;
    private String meaning;
    private String userEmail;


    public Word(String userEmail, String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
        this.userEmail = userEmail;
    }

    @Ignore
    public Word() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
