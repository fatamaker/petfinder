package com.example.petfinder.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.petfinder.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    // Durée d'affichage du splash screen (en millisecondes)
    private static final int SPLASH_DISPLAY_LENGTH = 2500; // 2.5 secondes

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Initialiser Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // Utiliser un Handler pour retarder la redirection
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                // Vérifier si l'utilisateur est déjà connecté
                FirebaseUser currentUser = mAuth.getCurrentUser();

                if (currentUser != null) {
                    // Utilisateur est connecté -> Aller à MainActivity
                    Intent mainIntent = new Intent(SplashActivity.this, DiscoverActivity.class);
                    startActivity(mainIntent);
                } else {
                    // Utilisateur n'est pas connecté -> Aller à LoginActivity
                    Intent loginIntent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                }

                // Fermer SplashActivity pour qu'on ne puisse pas y revenir avec "retour"
                finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}