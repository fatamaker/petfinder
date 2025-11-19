package com.example.petfinder.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petfinder.adapters.AnnouncementAdapter;
import com.example.petfinder.models.Announcement;
import com.example.petfinder.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    private ImageView imgProfile;
    private TextView tvUserName, tvUserBio;
    private Button btnAddAnnouncement;
    private RecyclerView rvAnnouncements;

    private static final int PICK_IMAGE = 100;
    private Uri imageUri;

    private List<Announcement> announcementList;
    private AnnouncementAdapter adapter;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Init UI
        imgProfile = view.findViewById(R.id.imgProfile);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserBio = view.findViewById(R.id.tvUserBio);
        btnAddAnnouncement = view.findViewById(R.id.btnAddAnnouncement);
        rvAnnouncements = view.findViewById(R.id.rvAnnouncements);

        // RecyclerView setup
        announcementList = new ArrayList<>();
        adapter = new AnnouncementAdapter(getContext(), announcementList);
        rvAnnouncements.setLayoutManager(new LinearLayoutManager(getContext()));
        rvAnnouncements.setAdapter(adapter);

        // Click profile image to choose a new photo
        imgProfile.setOnClickListener(v -> openGallery());

        // Click add announcement button
        btnAddAnnouncement.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Navigate to Add Announcement screen", Toast.LENGTH_SHORT).show();
            // TODO: Open AddFragment or activity
        });

        // Dummy data
        loadDummyAnnouncements();
    }

    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK);
        gallery.setType("image/*");
        startActivityForResult(gallery, PICK_IMAGE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK && data != null) {
            imageUri = data.getData();
            imgProfile.setImageURI(imageUri);
        }
    }

    private void loadDummyAnnouncements() {
        // Add fake announcements for preview
        announcementList.add(new Announcement("Black Cat", "Found hiding under a car...", "May 26, 2024", "found", R.drawable.found2));
        announcementList.add(new Announcement("Parrot", "Found in a park...", "May 27, 2024", "found", R.drawable.found2));
        adapter.notifyDataSetChanged();
    }
}
