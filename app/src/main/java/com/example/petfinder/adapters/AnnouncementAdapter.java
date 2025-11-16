package com.example.petfinder.adapters;

import android.content.Context;
// import android.graphics.Color; // Suppression de l'import inutile
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
// import androidx.core.content.ContextCompat; // Suppression de l'import inutile
import androidx.recyclerview.widget.RecyclerView;

import com.example.petfinder.R;
import com.example.petfinder.models.Announcement;

import java.util.List;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder> {

    private final Context context;
    private List<Announcement> announcements;

    // ✅ Constructeur (Non fourni, mais nécessaire)
    public AnnouncementAdapter(Context context, List<Announcement> announcements) {
        this.context = context;
        this.announcements = announcements;
    }

    // ✅ Méthode pour mettre à jour la liste (Non fourni, mais utile)
    public void updateAnnouncements(List<Announcement> newAnnouncements) {
        this.announcements = newAnnouncements;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_announcement, parent, false);
        return new AnnouncementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementViewHolder holder, int position) {
        Announcement announcement = announcements.get(position);

        // Affichage des données
        holder.petName.setText("Name: " + announcement.getPetName());
        holder.locationText.setText(announcement.getDescription()); // Description contient le lieu/détails
        holder.dateText.setText(announcement.getDate());

        // Logique de chargement d'image (Placeholder utilisé, comme dans votre version)
        if (announcement.getImageUrl() != null && !announcement.getImageUrl().isEmpty()) {
            // Utiliser Glide ici si vous l'avez : Glide.with(context).load(announcement.getImageUrl()).into(holder.petImage);

            // Placeholder ou logique pour charger l'image locale (si vous avez utilisé la Solution 1)
            holder.petImage.setImageResource(R.drawable.ic_dog_category);
        } else {
            holder.petImage.setImageResource(R.drawable.ic_dog_category); // Placeholder par défaut
        }
    }

    @Override
    public int getItemCount() {
        return announcements.size();
    }

    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder {
        ImageView petImage;
        TextView petName;
        TextView locationText;
        TextView dateText;

        public AnnouncementViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initialisation des vues (comme corrigé)
            petImage = itemView.findViewById(R.id.petImage);
            petName = itemView.findViewById(R.id.petName);
            locationText = itemView.findViewById(R.id.locationText);
            dateText = itemView.findViewById(R.id.dateText);
        }
    }
}