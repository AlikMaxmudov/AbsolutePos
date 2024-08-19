package com.example.absolutepos;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class createfirstistu extends Fragment {

    private CheckBox checkBox;
    private EditText editTextSystemAutomas;
    private TextView textViewSystemAutomas;
    private EditText editTextInstitutionName;
    private EditText editTextCityAndCountry;
    private EditText editTextAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_createfirstistu, container, false);

        checkBox = view.findViewById(R.id.checkBox);
        editTextSystemAutomas = view.findViewById(R.id.systemautomas);
        textViewSystemAutomas = view.findViewById(R.id.textsystemautomas);
        editTextInstitutionName = view.findViewById(R.id.nameinst); // Assuming IDs are correct
        editTextCityAndCountry = view.findViewById(R.id.cityinst);
        editTextAddress = view.findViewById(R.id.adressints);

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editTextSystemAutomas.setVisibility(View.VISIBLE);
                textViewSystemAutomas.setVisibility(View.VISIBLE);
            } else {
                editTextSystemAutomas.setVisibility(View.GONE);
                textViewSystemAutomas.setVisibility(View.GONE);
            }
        });

        return view;
    }

    // Method to save data
    public void saveCreateData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("institution_name", editTextInstitutionName.getText().toString());
        editor.putString("city_and_country", editTextCityAndCountry.getText().toString());
        editor.putString("address", editTextAddress.getText().toString());
        editor.putString("system_automas", editTextSystemAutomas.getText().toString());
        editor.putString("text_system_automas", textViewSystemAutomas.getText().toString());
        editor.apply();
    }
}
