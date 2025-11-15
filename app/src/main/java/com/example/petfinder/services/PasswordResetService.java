package com.example.petfinder.services;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class PasswordResetService {

    private final FirebaseAuth auth;

    public PasswordResetService() {
        this.auth = FirebaseAuth.getInstance();
    }

    public void sendPasswordResetEmail(String email, ResetCallback callback) {
        if (email == null || email.trim().isEmpty()) {
            callback.onFailure("Veuillez entrer une adresse e-mail valide.");
            return;
        }

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onSuccess("Un lien de réinitialisation a été envoyé.");
                    } else {
                        Exception exception = task.getException();
                        if (exception instanceof FirebaseAuthInvalidUserException) {
                            callback.onSuccess("Si cet e-mail existe, un lien a été envoyé.");
                        } else {
                            callback.onFailure("Erreur réseau. Veuillez réessayer.");
                        }
                    }
                });
    }

    public interface ResetCallback {
        void onSuccess(String message);
        void onFailure(String errorMessage);
    }
}
