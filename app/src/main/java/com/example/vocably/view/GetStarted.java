package com.example.vocably.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.vocably.R;
import com.example.vocably.auth.Login;

public class GetStarted extends AppCompatActivity {

    private Button btnGetStarted;
    private static final String PREFS_NAME = "vocably_prefs";
    private static final String KEY_ONBOARDING_DONE = "onboarding_done";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_started);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        if (prefs.getBoolean(KEY_ONBOARDING_DONE, false)) {
            startLoginAndFinish();
            return;
        }

        ImageView animLogo = findViewById(R.id.animGetStartedLogo);
        btnGetStarted = findViewById(R.id.btnGetStarted);

        Glide.with(this).asGif().
                load(R.drawable.vocably_gs).into(animLogo);

        btnGetStarted.setOnClickListener(V -> {
            prefs.edit().putBoolean(KEY_ONBOARDING_DONE, true).apply();
            startLoginAndFinish();
        });

    }

    private void startLoginAndFinish() {
        Intent intent = new Intent(GetStarted.this, Login.class);
        startActivity(intent);
        finish();
    }
}