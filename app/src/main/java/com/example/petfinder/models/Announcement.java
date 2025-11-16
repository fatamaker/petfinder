package com.example.petfinder.models;


// Représente une annonce d'animal trouvé ou perdu
public class Announcement {
    // Les champs de Firebase doivent avoir un constructeur vide pour la désérialisation
    public String petName;
    public String description; // Emplacement/détails de la découverte ou de la perte
    public String date;
    public String status; // "found" ou "lost"
    public String imageUrl; // URL de l'image (Firebase Storage ou autre)
    public String userId; // ID de l'utilisateur qui a posté l'annonce

    public int imageResourceId; // NOUVEAU CHAMP : Pour stocker l'ID de la ressource locale

    // Constructeur vide (inchangé)
    public Announcement() { }

    // Constructeur complet à 6 Strings (inchangé)
    public Announcement(String petName, String description, String date, String status, String imageUrl, String userId) {
        this.petName = petName;
        this.description = description;
        this.date = date;
        this.status = status;
        this.imageUrl = imageUrl;
        this.userId = userId;
        this.imageResourceId = 0; // Par défaut à 0
    }

    // NOUVEAU CONSTRUCTEUR (pour la simulation dans DiscoverActivity.java)
    public Announcement(String petName, String description, String date, String status, int imageResourceId) {
        this.petName = petName;
        this.description = description;
        this.date = date;
        this.status = status;
        this.imageResourceId = imageResourceId;
        this.imageUrl = null; // Pas d'URL si on utilise la ressource locale
        this.userId = null;
    }

    // NOUVEAU GETTER
    public int getImageResourceId() { return imageResourceId; }

    // Getters (nécessaires pour l'accès aux propriétés et l'utilisation de Firebase)
    public String getPetName() { return petName; }
    public String getDescription() { return description; }
    public String getDate() { return date; }
    public String getStatus() { return status; }
    public String getImageUrl() { return imageUrl; }
    public String getUserId() { return userId; }
}