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

                String user = editUser.getText().toString();
                String email = editEmail.getText().toString();
                String password = editPass1.getText().toString();
                String confirmPassword = editPass2.getText().toString();

                helper = new DatabaseHelper(view.getContext());

                if (user.equals("") || email.equals("") || password.equals("") || confirmPassword.equals(""))
                    Toast.makeText(view.getContext(), "llena todos los campos", Toast.LENGTH_SHORT).show();
                else {
                    if (password.equals(confirmPassword)) {
                        Boolean checkUser = helper.checkUser(email);
                        if (checkUser == false) {
                            Boolean insert = helper.insertUser(email, user, password);
                            if (insert == true) {
                                Toast.makeText(view.getContext(), "INGRESO EXITOSO", Toast.LENGTH_SHORT).show();
                                MainActivity mainActivity = (MainActivity) getActivity();
                                mainActivity.setEmail(email);
                                mainActivity.setMenuEnabled(true);
                                mainActivity.replaceFragment(new UserPage(email));

                            } else {
                                Toast.makeText(view.getContext(), "ingreso fallido", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(view.getContext(), "email ya registrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(view.getContext(), "contrasena incorrecta", Toast.LENGTH_SHORT).show();
                    }
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