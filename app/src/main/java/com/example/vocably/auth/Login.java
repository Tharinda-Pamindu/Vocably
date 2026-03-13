package com.example.vocably.auth;

import static android.content.ContentValues.TAG;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import com.example.vocably.MainActivity;
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

        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        clickToRegister = findViewById(R.id.clickToRegister);

        try {
            Intent registeredUser = getIntent();
            if (registeredUser != null) {
                String email = registeredUser.getStringExtra("EMAIL");
                if (email != null) {
                    txtEmail.getEditText().setText(email);
                }
            }
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
        }

        mAuth = FirebaseAuth.getInstance();

        clickToRegister.setOnClickListener(V -> {
            Intent intent = new Intent(Login.this, Register.class);

            Bundle bundle = ActivityOptions.makeCustomAnimation(this,
                    android.R.anim.slide_in_left,
                    android.R.anim.slide_out_right).toBundle();

            startActivity(intent, bundle);
            finish();
        });

        btnLogin.setOnClickListener(V -> {
            String email = txtEmail.getEditText().getText().toString();
            String password = txtPassword.getEditText().getText().toString();

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(this, "Please enter your email", Toast.LENGTH_LONG).show();
            } else if (TextUtils.isEmpty(password)) {
                Toast.makeText(this, "Please enter your password", Toast.LENGTH_LONG).show();
            } else {
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent dashBoardIntent = new Intent(Login.this, MainActivity.class);
                                    dashBoardIntent.putExtra("EMAIL", email);
                                    Bundle bundle = ActivityOptions.makeCustomAnimation(Login.this,
                                            android.R.anim.slide_in_left,
                                            android.R.anim.slide_out_right).toBundle();
                                    startActivity(dashBoardIntent, bundle);
                                    finish();

                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });

    }
}