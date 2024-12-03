package com.example.starbell;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Fragment {

    DatabaseHelper helper;
    EditText editUser, editEmail, editPass1, editPass2;
    Button btn1, btn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_register, container, false);

        editUser = (EditText) view.findViewById(R.id.user_et);
        editEmail = (EditText) view.findViewById(R.id.email_et);
        editPass1 = (EditText) view.findViewById(R.id.pass_et);
        editPass2 = (EditText) view.findViewById(R.id.pass2_et);

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String user = editUser.getText().toString().trim();
                    String email = editEmail.getText().toString().trim();
                    String password = editPass1.getText().toString().trim();
                    String confirmPassword = editPass2.getText().toString().trim();

                    helper = new DatabaseHelper(view.getContext());

                    if (user.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                        Toast.makeText(view.getContext(), "Llena todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (!password.equals(confirmPassword)) {
                        Toast.makeText(view.getContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Boolean checkUser = helper.checkUser(email);
                    if (checkUser) {
                        Toast.makeText(view.getContext(), "El email ya está registrado", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Boolean insert = helper.insertUser(email, user, password);
                    if (insert) {
                        Toast.makeText(view.getContext(), "Ingreso exitoso", Toast.LENGTH_SHORT).show();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        if (mainActivity != null) {
                            mainActivity.setEmail(email);
                            mainActivity.setMenuEnabled(true);
                            mainActivity.replaceFragment(new UserPage(email));
                        }
                    } else {
                        Toast.makeText(view.getContext(), "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("UserRegistration", "Error en el registro: " + e.getMessage(), e);
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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