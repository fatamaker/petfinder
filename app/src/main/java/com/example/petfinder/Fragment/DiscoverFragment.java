package com.example.petfinder.Fragment;


import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petfinder.R;
import com.example.petfinder.adapters.AnnouncementAdapter;
import com.example.petfinder.adapters.CategoryAdapter;
import com.example.petfinder.models.Announcement;
import com.example.petfinder.models.Category;

import java.util.ArrayList;
import java.util.List;

public class DiscoverFragment extends Fragment {

    private TextView foundText;
    private TextView lostText;
    private RecyclerView categoriesRecyclerView;
    private RecyclerView announcementsRecyclerView;

    private int colorWhite;
    private int colorGrayDark;
    private int selectedBackground;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discover, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init Views
        foundText = view.findViewById(R.id.foundText);
        lostText = view.findViewById(R.id.lostText);
        categoriesRecyclerView = view.findViewById(R.id.categoriesRecyclerView); announcementsRecyclerView = view.findViewById(R.id.announcementsRecyclerView);

        // Init Colors

        Context context = getContext();
        if (context != null) {
            colorWhite = ContextCompat.getColor(context, R.color.white);
            colorGrayDark = ContextCompat.getColor(context, R.color.gray_dark);
            selectedBackground = R.drawable.bg_tab_selected;
        }

        // Clicks for Found / Lost tabs
        foundText.setOnClickListener(v -> selectTab(foundText, lostText, "found"));
        lostText.setOnClickListener(v -> selectTab(lostText, foundText, "lost"));

        // Setup RecyclerViews
        setupCategoriesRecyclerView();
        setupAnnouncementsRecyclerView();

        // Load default
        loadAnnouncements("found");
    }

    private void selectTab(TextView selected, TextView unselected, String type) {
        selected.setTextColor(colorWhite);
        selected.setBackgroundResource(selectedBackground);
        selected.setTypeface(null, Typeface.BOLD);

        unselected.setTextColor(colorGrayDark);
        unselected.setBackgroundResource(0);
        unselected.setTypeface(null, Typeface.BOLD);

        loadAnnouncements(type);
    }

    private void setupCategoriesRecyclerView() {
        categoriesRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false)
        );

        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Dog", R.drawable.ic_dog_category));
        categories.add(new Category("Cat", R.drawable.ic_cat_category));
        categories.add(new Category("Bird", R.drawable.ic_bird_category));
        categories.add(new Category("Other", R.drawable.ic_other_category));

        CategoryAdapter adapter = new CategoryAdapter(getContext(), categories);
        categoriesRecyclerView.setAdapter(adapter);
    }

    private void setupAnnouncementsRecyclerView() {
        announcementsRecyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false)
        );
    }

    private void loadAnnouncements(String status) {

        List<Announcement> announcements = new ArrayList<>();

        if (status.equals("found")) {
            announcements.add(new Announcement("Black Cat", "Found hiding under a car...", "May 26, 2024", "found", R.drawable.found2));
            announcements.add(new Announcement("Parrot", "Found in a park...", "May 27, 2024", "found", R.drawable.found2));
        } else {
            announcements.add(new Announcement("Max (Dog)", "Lost near the school...", "May 25, 2024", "lost", R.drawable.found2));
        }

        AnnouncementAdapter adapter = new AnnouncementAdapter(getContext(), announcements);
        announcementsRecyclerView.setAdapter(adapter);
    }
}
