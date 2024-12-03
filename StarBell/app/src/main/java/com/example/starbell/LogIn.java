package com.example.starbell;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogIn extends Fragment {

    EditText edit1, edit2;
    Button btn1, btn2;
    DatabaseHelper helper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_log_in, container, false);
        edit1 = (EditText) view.findViewById(R.id.et_user_login);
        edit2 = (EditText) view.findViewById(R.id.et_pass_login);

        helper = new DatabaseHelper(view.getContext());

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String email = edit1.getText().toString();
                    String password = edit2.getText().toString();

                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(view.getContext(), "Llena todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Boolean checkCredentials = helper.checkUserPassword(email, password);

                    if (checkCredentials) {
                        Toast.makeText(view.getContext(), "ÉXITO", Toast.LENGTH_SHORT).show();

                        MainActivity mainActivity = (MainActivity) getActivity();
                        if (mainActivity != null) {
                            mainActivity.setEmail(email);
                            mainActivity.setMenuEnabled(true);
                            mainActivity.replaceFragment(new UserPage(email));
                        }
                    } else {
                        Toast.makeText(view.getContext(), "Datos inválidos", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), "Ocurrió un error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.replaceFragment(new Principal());
            }
        });

        return view;
    }
}