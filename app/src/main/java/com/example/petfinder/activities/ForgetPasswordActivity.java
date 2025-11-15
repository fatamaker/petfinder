package com.example.petfinder.activities;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.example.petfinder.R;
import com.example.petfinder.services.PasswordResetService;

public class ForgetPasswordActivity extends AppCompatActivity {

    private TextInputEditText emailEditText;
    private MaterialButton sendResetLinkButton;
    private ImageButton backButton;
    private ProgressBar progressBar;
    private PasswordResetService resetService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        emailEditText = findViewById(R.id.emailEditText);
        sendResetLinkButton = findViewById(R.id.sendResetLinkButton);
        backButton = findViewById(R.id.backButton);
        progressBar = findViewById(R.id.progressBar);

        resetService = new PasswordResetService();

        backButton.setOnClickListener(v -> finish());

        sendResetLinkButton.setOnClickListener(v -> handlePasswordReset());
    }

    private void handlePasswordReset() {
        String email = emailEditText.getText().toString().trim();

        if (!validateEmail(email)) return;

        setLoading(true);

        resetService.sendPasswordResetEmail(email, new PasswordResetService.ResetCallback() {
            @Override
            public void onSuccess(String message) {
                setLoading(false);
                Toast.makeText(ForgetPasswordActivity.this, message, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(String errorMessage) {
                setLoading(false);
                Toast.makeText(ForgetPasswordActivity.this, errorMessage, Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()) {
            emailEditText.setError("L'e-mail est requis.");
            emailEditText.requestFocus();
            return false;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailEditText.setError("Format d'e-mail invalide.");
            emailEditText.requestFocus();
            return false;
        }
        return true;
    }

    private void setLoading(boolean isLoading) {
        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
            sendResetLinkButton.setText("");
            sendResetLinkButton.setEnabled(false);
        } else {
            progressBar.setVisibility(View.GONE);
            sendResetLinkButton.setText("Get Started");
            sendResetLinkButton.setEnabled(true);
        }
    }
}
