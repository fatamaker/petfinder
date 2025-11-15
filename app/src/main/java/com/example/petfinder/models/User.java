package com.example.petfinder.models;

// Un POJO (Plain Old Java Object) utilisé pour mapper les données entre l'application et Firestore.
public class User {

    private String userId;
    private String fullName;
    private String email;
    private String phone;
    private String gender;
    private String profileImageUrl;


    // 2. Constructeur principal (Utilisé lors de l'inscription pour créer un nouvel utilisateur)
    public User(String userId, String fullName, String email, String phone) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;

        // Champs optionnels ou ajoutés après l'inscription
        this.gender = "";
        this.profileImageUrl = "";
    }

    // 3. Getters et Setters (Obligatoires pour Firebase/Firestore)

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }
}