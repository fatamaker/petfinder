package com.example.petfinder.models;

// Représente une seule catégorie d'animal (Chien, Chat, etc.)
public class Category {
    private String name;
    private int iconResourceId;

    public Category(String name, int iconResourceId) {
        this.name = name;
        this.iconResourceId = iconResourceId;
    }

    public String getName() {
        return name;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }
}