package com.example.vocably.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vocably.MainActivity;
import com.example.vocably.R;
import com.example.vocably.db.VocabularyDB;
import com.example.vocably.db.dao.WordDao;
import com.example.vocably.tts.TextToSpeechManager;

public class ViewWord extends AppCompatActivity {

    private TextToSpeechManager textToSpeechManager;
    private TextView txtWord, txtDescription;
    private WordDao wordDao;
    private String currentWord;

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
        ImageButton btnSpeakWord = findViewById(R.id.btnSpeakWord);

        Intent intent = getIntent();
        if (intent != null) {
            String wordText = intent.getStringExtra("word_text");
            String wordDescription = intent.getStringExtra("word_description");
            if (wordText != null) {
                currentWord = wordText;
                txtWord.setText(wordText);
            }
            if (wordDescription != null) {
                txtDescription.setText(wordDescription);
            }
        }

        VocabularyDB db = VocabularyDB.getInstance(this);
        wordDao = db.wordDao();

        textToSpeechManager = new TextToSpeechManager(this, new TextToSpeechManager.InitListener() {
            @Override
            public void onReady() {
                // no-op
            }

            @Override
            public void onError(String message) {
                Toast.makeText(ViewWord.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        btnSpeakWord.setOnClickListener(v -> {
            if (currentWord == null || currentWord.trim().isEmpty()) {
                Toast.makeText(ViewWord.this, "No word to pronounce", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!textToSpeechManager.isReady()) {
                Toast.makeText(ViewWord.this, "Text-to-speech is loading", Toast.LENGTH_SHORT).show();
                return;
            }
            textToSpeechManager.speak(currentWord);
        });

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

    @Override
    protected void onDestroy() {
        if (textToSpeechManager != null) {
            textToSpeechManager.stop();
            textToSpeechManager.shutdown();
        }
        super.onDestroy();
    }
}