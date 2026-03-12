package com.example.vocably.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vocably.MainActivity;
import com.example.vocably.R;
import com.example.vocably.db.VocabularyDB;
import com.example.vocably.db.dao.WordDao;

public class ViewWord extends AppCompatActivity {

    private TextView txtWord, txtDescription;

    private WordDao wordDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_word);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtWord = findViewById(R.id.lblWord);
        txtDescription = findViewById(R.id.lblDescription);
        Button btnDeleteWord = findViewById(R.id.btnDeleteWord);

        Intent intent = getIntent();
        if (intent != null) {
            String wordText = intent.getStringExtra("word_text");
            String wordDescription = intent.getStringExtra("word_description");
            if (wordText != null) {
                txtWord.setText(wordText);
            }
            if (wordDescription != null) {
                txtDescription.setText(wordDescription);
            }
        }

        VocabularyDB db = VocabularyDB.getInstance(this);
        wordDao = db.wordDao();

        btnDeleteWord.setOnClickListener(V -> {
            String wordText = intent.getStringExtra("word_text");
            String wordDescription = intent.getStringExtra("word_description");
            if (wordText != null && wordDescription != null) {
                wordDao.delete(wordText);
            }
            startActivity(new Intent(ViewWord.this, MainActivity.class));
            finish();
        });
    }
}