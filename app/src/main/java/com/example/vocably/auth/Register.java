package com.example.vocably.auth;

import static android.content.ContentValues.TAG;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

public class Register extends AppCompatActivity {

    private TextInputLayout txtEmail, txtPassword, txtConfirmPassword;
    private Button btnRegister;
    private TextView clickToLogin;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        clickToLogin = findViewById(R.id.clickToLogin);

        clickToLogin.setOnClickListener(V -> {
            //TODO : Implement redirect to the login activity functionality
            Intent intent = new Intent(Register.this, Login.class);

            Bundle bundle = ActivityOptions.makeCustomAnimation(Register.this,
                    android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left).toBundle();

            startActivity(intent, bundle);
            finish();
        });

        btnRegister.setOnClickListener(V -> {
            //TODO : Implement register functionality

            String email = txtEmail.getEditText().getText().toString();
            String password = txtPassword.getEditText().getText().toString();
            String confirmPassword = txtConfirmPassword.getEditText().getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
                return;
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
                return;
            } else if (TextUtils.isEmpty(confirmPassword)) {
                Toast.makeText(this, "Please confirm your password", Toast.LENGTH_LONG).show();
                return;
            } else if (!TextUtils.equals(password, confirmPassword)) {
                Toast.makeText(this, "Passwords do not match", Toast.LENGTH_LONG).show();
                return;
            } else {
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "createUserWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Intent intent = new Intent(Register.this, Login.class);
                                    intent.putExtra("EMAIL", email);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(Register.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                txtEmail.getEditText().setText("");
                txtPassword.getEditText().setText("");
                txtConfirmPassword.getEditText().setText("");
                Toast.makeText(this, "Registration successful", Toast.LENGTH_LONG).show();
            }
        });

    }
}