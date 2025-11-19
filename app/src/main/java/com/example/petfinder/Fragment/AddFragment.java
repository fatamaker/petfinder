package com.example.petfinder.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petfinder.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;

public class AddFragment extends Fragment {

    // UI elements
    private TextInputLayout tilDate;
    private EditText etDate;

    private ImageView imgPreview;

    private CheckBox cbDog, cbCat, cbRabbit, cbOther;

    // For image selection
    private static final int PICK_IMAGE = 100;
    private Uri imageUri;

    public AddFragment() {
        // Required empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // 1️⃣ Initialize views
        imgPreview = view.findViewById(R.id.imgPreview);
        tilDate = view.findViewById(R.id.tilDate);
        etDate = tilDate.getEditText();

        cbDog = view.findViewById(R.id.cbDog);
        cbCat = view.findViewById(R.id.cbCat);
        cbRabbit = view.findViewById(R.id.cbRabbit);
        cbOther = view.findViewById(R.id.cbOther);

        // 2️⃣ Image selection
        imgPreview.setOnClickListener(v -> openGallery());

        // 3️⃣ Single selection logic for checkboxes
        setupSingleSelectionCheckbox(cbDog);
        setupSingleSelectionCheckbox(cbCat);
        setupSingleSelectionCheckbox(cbRabbit);
        setupSingleSelectionCheckbox(cbOther);

        // 4️⃣ Date picker logic
        tilDate.setEndIconOnClickListener(v -> showDatePicker());
        if (etDate != null) {
            etDate.setFocusable(false);
            etDate.setOnClickListener(v -> showDatePicker());
        }
    }

    // Open gallery to pick image
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(gallery, PICK_IMAGE);
    }

    // Handle gallery result
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgPreview.setImageURI(imageUri);
        }
    }

    // Show DatePickerDialog
    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        if (getContext() != null) {
            DatePickerDialog dialog = new DatePickerDialog(
                    getContext(),
                    (DatePicker view, int selectedYear, int selectedMonth, int selectedDay) -> {
                        String date = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        if (etDate != null) etDate.setText(date);
                    },
                    year, month, day
            );
            dialog.show();
        }
    }

    // Ensure only one checkbox is selected at a time
    private void setupSingleSelectionCheckbox(CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                cbDog.setChecked(checkBox == cbDog);
                cbCat.setChecked(checkBox == cbCat);
                cbRabbit.setChecked(checkBox == cbRabbit);
                cbOther.setChecked(checkBox == cbOther);
            }
        });
    }
}
