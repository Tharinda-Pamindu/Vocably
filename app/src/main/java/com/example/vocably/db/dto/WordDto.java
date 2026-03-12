package com.example.vocably.db.dto;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class WordDto implements Serializable {

    private String word;
    private String description;

    public WordDto(String word, String description) {
        this.word = word;
        this.description = description;
    }

    public WordDto() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "WordDto{" +
                "word='" + word + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
