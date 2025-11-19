package com.example.petfinder.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;







import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.petfinder.Fragment.AddFragment;
import com.example.petfinder.Fragment.DiscoverFragment;
import com.example.petfinder.Fragment.MapFragment;
import com.example.petfinder.Fragment.ProfileFragment;
import com.example.petfinder.R;
import com.example.petfinder.adapters.CategoryAdapter;
import com.example.petfinder.adapters.AnnouncementAdapter;
import com.example.petfinder.models.Category;
import com.example.petfinder.models.Announcement;

import java.util.ArrayList;
import java.util.List;

public class DiscoverActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discover);

        // 1. Initialiser le fragment par défaut (Home/Discover)
        if (savedInstanceState == null) {
            // Assurez-vous d'avoir un DiscoverFragment ou HomeFragment
            replaceFragment(new DiscoverFragment());
        }

        // 2. Récupérer le conteneur inclus de la barre de navigation
        View bottomNavInclude = findViewById(R.id.bottom_navigation_include);

        // 3. Trouver les vues par ID à l'intérieur du conteneur inclus
        LinearLayout navHome = bottomNavInclude.findViewById(R.id.nav_home);
        LinearLayout navMap = bottomNavInclude.findViewById(R.id.nav_map);
        LinearLayout navAdd = bottomNavInclude.findViewById(R.id.nav_add);
        LinearLayout navProfile = bottomNavInclude.findViewById(R.id.nav_profile);

        // 4. Définir les écouteurs de clic
        navHome.setOnClickListener(v -> replaceFragment(new DiscoverFragment()));
        navMap.setOnClickListener(v -> replaceFragment(new MapFragment()));
        navAdd.setOnClickListener(v -> replaceFragment(new AddFragment()));
       navProfile.setOnClickListener(v -> replaceFragment(new ProfileFragment()));
    }

    /**
     * Méthode générique pour remplacer le fragment actuel dans le conteneur.
     * @param fragment Le nouveau fragment à afficher.
     */
    private void replaceFragment(Fragment fragment) {
        // Obtenir le FragmentManager
        FragmentManager fragmentManager = getSupportFragmentManager();
        // Commencer une transaction
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Remplacer l'ancien fragment par le nouveau dans le FrameLayout
        transaction.replace(R.id.fragment_container, fragment);

        // Optionnel : Ajouter à la pile de retour pour la gestion du bouton 'Retour'
        // transaction.addToBackStack(null);

        // Valider la transaction
        transaction.commit();
    }


}