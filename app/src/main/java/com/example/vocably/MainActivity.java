package com.example.vocably;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.vocably.adapter.WordAdapter;
import com.example.vocably.db.VocabularyDB;
import com.example.vocably.db.dao.WordDao;
import com.example.vocably.model.Word;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private WordDao wordDao;

    private FloatingActionButton btnAddWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.listOfWords);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        VocabularyDB db = VocabularyDB.getInstance(this);
        wordDao = db.wordDao();

        wordDao.getAll().observe(this, words -> {
            recyclerView.setAdapter(new WordAdapter(words, getLayoutInflater()));
        });

        btnAddWord = findViewById(R.id.btnAddWord);
        btnAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(MainActivity.this);
                bottomSheetDialog.setContentView(R.layout.word_add_sheet_layout);

                TextInputLayout txtEnterdWord = bottomSheetDialog.findViewById(R.id.txtEnterdWord);
                TextInputLayout txtEnterdDescription = bottomSheetDialog.findViewById(R.id.txtEnterdDescription);

                Button btnGenerateDescription = bottomSheetDialog.findViewById(R.id.btnGenerateDescription);
                //TODO: Implement generate description functionality using LLM

                Button btnAddWord = bottomSheetDialog.findViewById(R.id.btnAddWord);
                btnAddWord.setOnClickListener(V -> {
                    wordDao.insert(new Word(txtEnterdWord.getEditText().getText().toString(), txtEnterdDescription.getEditText().getText().toString()));
                    bottomSheetDialog.dismiss();
                });

                Log.i("INFO", txtEnterdWord.getEditText().getText().toString());
                Log.i("INFO", txtEnterdDescription.getEditText().getText().toString());

                bottomSheetDialog.show();
            }
        });

    }
}