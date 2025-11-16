package com.example.petfinder.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;







import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petfinder.R;
import com.example.petfinder.adapters.CategoryAdapter;
import com.example.petfinder.adapters.AnnouncementAdapter;
import com.example.petfinder.models.Category;
import com.example.petfinder.models.Announcement;

import java.util.ArrayList;
import java.util.List;

public class DiscoverActivity extends AppCompatActivity {

    private RecyclerView categoriesRecyclerView;
    private RecyclerView announcementsRecyclerView;

    // Assurez-vous d'implémenter vos adaptateurs et modèles
    // Ex: CategoryAdapter, AnnouncementAdapter, Category.java, Announcement.java

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);
        announcementsRecyclerView = findViewById(R.id.announcementsRecyclerView);

        // 1. Initialiser le RecyclerView des Catégories (Horizontal)
        setupCategoriesRecyclerView();

        // 2. Initialiser le RecyclerView des Annonces (Vertical)
        setupAnnouncementsRecyclerView();

        // Gérer les clics sur les onglets Found/Lost (Exemple)
        findViewById(R.id.foundText).setOnClickListener(v -> loadAnnouncements("found"));
        findViewById(R.id.lostText).setOnClickListener(v -> loadAnnouncements("lost"));
    }

    private void setupCategoriesRecyclerView() {
        // Définir le LayoutManager pour une liste horizontale
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Simuler les données des catégories
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Dog", R.drawable.ic_dog_category));
        categories.add(new Category("Cat", R.drawable.ic_cat_category));
        categories.add(new Category("Bird", R.drawable.ic_bird_category));
        categories.add(new Category("Other", R.drawable.ic_other_category));
        // ... ajoutez d'autres catégories

        // Attacher l'adaptateur
        CategoryAdapter adapter = new CategoryAdapter(this, categories);
        categoriesRecyclerView.setAdapter(adapter);
    }

    private void setupAnnouncementsRecyclerView() {
        // Définir le LayoutManager pour une liste verticale
        announcementsRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Charger les annonces par défaut (Found)
        loadAnnouncements("found");
    }

    /**
     * Charge et affiche la liste des annonces (simule une requête Firebase).
     * @param status "found" ou "lost"
     */
    private void loadAnnouncements(String status) {
        // --- LOGIQUE DE RÉCUPÉRATION DE DONNÉES DE FIREBASE FIRESTORE ICI ---

        List<Announcement> announcements = new ArrayList<>();

        if (status.equals("found")) {
            announcements.add(new Announcement("Black Cat", "Found hiding under a car...", "May 26, 2024", "found", R.drawable.found));
            announcements.add(new Announcement("Parrot", "Found in a park...", "May 27, 2024", "found", R.drawable.found));
        } else {
            announcements.add(new Announcement("Max (Dog)", "Lost near the school...", "May 25, 2024", "lost", R.drawable.found));
            // ... autres annonces perdues
        }

        // Mettre à jour l'adaptateur
        AnnouncementAdapter adapter = new AnnouncementAdapter(this, announcements);
        announcementsRecyclerView.setAdapter(adapter);

        // Mettre à jour le style des onglets

    }


}