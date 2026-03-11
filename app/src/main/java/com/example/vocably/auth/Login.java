package com.example.vocably.auth;

import static android.content.ContentValues.TAG;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.vocably.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private TextInputLayout txtEmail, txtPassword;
    private Button btnLogin;
    private TextView clickToRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try{
            Intent registeredUser = getIntent();
            if (registeredUser != null) {
                String email = registeredUser.getStringExtra("EMAIL");
                txtEmail.getEditText().setText(email);
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        mAuth = FirebaseAuth.getInstance();

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        clickToRegister = findViewById(R.id.clickToRegister);

        clickToRegister.setOnClickListener(V -> {
            Intent intent = new Intent(Login.this, Register.class);

            Bundle bundle = ActivityOptions.makeCustomAnimation(this,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right).toBundle();

            startActivity(intent, bundle);
            finish();
        });

        btnLogin.setOnClickListener(V -> {
            //TODO : Implement login functionality
        });

    }
}