package com.example.vocably;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GetStarted extends AppCompatActivity {

    Button btnGetStarted;
    TextView loginText;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_started); // Must match XML file name

        btnGetStarted = findViewById(R.id.btnGetStarted);
        loginText = findViewById(R.id.loginText);

        // When Get Started is clicked
        btnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GetStarted.this, "Welcome to Vocably !", Toast.LENGTH_SHORT).show();
                // Later you can navigate to LoginActivity here
            }
        });

        // Login text click
        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(GetStarted.this, "Login page not ready yet!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}