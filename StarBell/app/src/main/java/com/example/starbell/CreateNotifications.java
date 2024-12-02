package com.example.starbell;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class CreateNotifications extends Fragment {

    private ListView listViewDays;
    private DayAdapter dayAdapter;
    private ArrayList<DayItem> dayList;
    private EditText nameEdit, descriptionEdit;
    private Spinner hourSpinner, minuteSpinner;
    private RadioGroup vibrationGroup;
    private RadioButton vibrationYes, vibrationNo;
    private Button btnConfirm;
    DatabaseHelper helper;
    public String email;

    public CreateNotifications(String email){
        this.email = email;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_notifications, container, false);

        listViewDays = view.findViewById(R.id.listViewDays);
        nameEdit = view.findViewById(R.id.name_edit);
        descriptionEdit = view.findViewById(R.id.description_edit);
        hourSpinner = view.findViewById(R.id.hour);
        minuteSpinner = view.findViewById(R.id.minute);
        vibrationGroup = view.findViewById(R.id.radioGroup);
        vibrationYes = view.findViewById(R.id.radioButtonAM);
        vibrationNo = view.findViewById(R.id.radioButtonPM);
        btnConfirm = view.findViewById(R.id.btnConfirm);

        setupDays();

        dayAdapter = new DayAdapter(getContext(), dayList);
        listViewDays.setAdapter(dayAdapter);

        minuteSpinner.setEnabled(false);

        ArrayList<String> hoursList = new ArrayList<>();
        for (int i = 0; i <= 24; i++) {
            hoursList.add(String.valueOf(i));
        }

        ArrayAdapter<String> hoursAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, hoursList);

        hoursAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hourSpinner.setAdapter(hoursAdapter);

        hourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedHour = hourSpinner.getSelectedItem().toString();

                minuteSpinner.setEnabled(true);

                ArrayList<String> minutesList = new ArrayList<>();
                if (selectedHour.equals("13")) {
                    minutesList.add("Eres");
                    minutesList.add("Real?");
                } else {
                    for (int i = 0; i < 60; i += 15) {
                        minutesList.add(String.valueOf(i));
                    }
                }

                ArrayAdapter<String> minutesAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, minutesList);

                minutesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                minuteSpinner.setAdapter(minutesAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                minuteSpinner.setEnabled(false);
            }
        });

        minuteSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedMinute = minuteSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btnConfirm.setOnClickListener(v -> {

            String name = nameEdit.getText().toString();
            String description = descriptionEdit.getText().toString();

            String hour = minuteSpinner.getSelectedItem().toString();

            String minute = minuteSpinner.getSelectedItem().toString();

            StringBuilder selectedDays = new StringBuilder("Días seleccionados:\n");
            for (DayItem day : dayList) {
                if (day.isChecked()) {
                    selectedDays.append(day.getDayName()).append("\n");
                }
            }
            Toast.makeText(getContext(), selectedDays.toString(), Toast.LENGTH_SHORT).show();

            helper = new DatabaseHelper(view.getContext());

            if (name.equals("") || hour.equals("") || minute.equals(""))
                Toast.makeText(view.getContext(), "llena todos los campos requeridos", Toast.LENGTH_SHORT).show();
            else {
                Boolean insert = helper.insertNotification(name, description, 1, 1, 1, "nose", email);
                if (insert == true) {
                    Toast.makeText(view.getContext(), "CREACION EXITOSA", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(view.getContext(), "creacion Fallida", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void setupDays() {
        dayList = new ArrayList<>();
        dayList.add(new DayItem("Lunes", false));
        dayList.add(new DayItem("Martes", false));
        dayList.add(new DayItem("Miércoles", false));
        dayList.add(new DayItem("Jueves", false));
        dayList.add(new DayItem("Viernes", false));
        dayList.add(new DayItem("Sábado", false));
        dayList.add(new DayItem("Domingo", false));
    }
}
