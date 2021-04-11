package com.example.sightsee;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sightsee.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    private EditText editEmail, editPassword;
    private Button btnRegister;
    private TextView tvLogin;
    private ProgressBar progressBar;
    private FirebaseAuth fAuth;
    private DatabaseReference databaseCases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fAuth = FirebaseAuth.getInstance();

        if (fAuth.getCurrentUser() != null) {
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();
        }

        editEmail = findViewById(R.id.editEmail);
        editPassword = findViewById(R.id.editPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);
        progressBar = findViewById(R.id.progressBar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String password = editPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    editEmail.setError(getString(R.string.email_error));
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    editEmail.setError(getString(R.string.password_error));
                    return;
                }
                if (password.length() <6) {
                    editEmail.setError(getString(R.string.password_length_error));
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                // register user
                fAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            CheckBox admin_checkbox = findViewById(R.id.admin_checkbox);
                            databaseCases = FirebaseDatabase.getInstance().getReference("users");
                            DatabaseReference newUserRef = databaseCases.push();
                            if (admin_checkbox.isChecked()) {
                                newUserRef.setValue(new User(email, getString(R.string.admin_type)));
                            }
                            else {
                                newUserRef.setValue(new User(email, getString(R.string.default_type)));
                            }
                            // String key = databaseCases.push().getKey();

                            Toast.makeText(com.example.sightsee.RegisterActivity.this,
                                    getString(R.string.user_created), Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        } else {
                            Toast.makeText(com.example.sightsee.RegisterActivity.this,
                                    getString(R.string.login_error_message)
                                            + task.getException().getMessage(),
                                    Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            }
        });
    }
}