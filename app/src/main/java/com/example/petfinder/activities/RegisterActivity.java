package com.example.petfinder.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petfinder.R;
import com.example.petfinder.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class RegisterActivity extends AppCompatActivity {

    // IMPORTANT : Tous les IDs correspondent aux derniers changements XML
    private TextInputEditText etFullName, etEmail, etPhone, etPassword, etConfirmPassword;
    private MaterialButton btnSignUp;
    private ImageButton btnBack;
    private TextView tvSignInLink; // Lien "Sign In"
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;
    private FirebaseFirestore mStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        // Lier les vues
        etFullName = findViewById(R.id.fullNameEditText);
        etEmail = findViewById(R.id.emailEditText);
        etPhone = findViewById(R.id.etPhoneRegister); // ID du nouveau champ téléphone
        etPassword = findViewById(R.id.passwordEditText);
        etConfirmPassword = findViewById(R.id.confirmPasswordEditText);
        btnSignUp = findViewById(R.id.registerButton);
        progressBar = findViewById(R.id.progressBar);
        btnBack = findViewById(R.id.backButton);
        tvSignInLink = findViewById(R.id.signInText);

        // Clic sur l'inscription
        btnSignUp.setOnClickListener(v -> registerUser());

        // Clic sur le bouton Retour
        btnBack.setOnClickListener(v -> onBackPressed());

        // Clic sur le lien "Sign In"
        tvSignInLink.setOnClickListener(v -> {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        });
    }

    private void registerUser() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        // --- Validation ---
        if (TextUtils.isEmpty(fullName)) { etFullName.setError("Required."); return; }
        if (TextUtils.isEmpty(email)) { etEmail.setError("Required."); return; }
        if (TextUtils.isEmpty(phone)) { etPhone.setError("Required."); return; }
        if (TextUtils.isEmpty(password)) { etPassword.setError("Required."); return; }
        if (password.length() < 6) { etPassword.setError("Must be >= 6 chars."); return; }
        if (!password.equals(confirmPassword)) { etConfirmPassword.setError("Passwords do not match."); return; }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                        String userId = firebaseUser.getUid();

                        // Création de l'objet User pour Firestore
                        User user = new User(userId, fullName, email, phone);

                        // Sauvegarde dans Firestore
                        mStore.collection("users").document(userId)
                                .set(user)
                                .addOnCompleteListener(firestoreTask -> {
                                    progressBar.setVisibility(View.GONE);
                                    if (firestoreTask.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "Account Created!", Toast.LENGTH_LONG).show();
                                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                        finish();
                                    } else {
                                        Toast.makeText(RegisterActivity.this, "Firestore Error: " + firestoreTask.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                    } else {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(RegisterActivity.this, "Auth Failed: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}