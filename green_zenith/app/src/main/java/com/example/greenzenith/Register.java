package com.example.greenzenith;

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
    EditText userET, emailET, ageET, pass1ET, pass2ET;
    Button btn1, btn2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_register, container, false);
        userET = (EditText) view.findViewById(R.id.edit1);
        emailET = (EditText) view.findViewById(R.id.edit2);
        ageET = (EditText) view.findViewById(R.id.edit3);
        pass1ET = (EditText) view.findViewById(R.id.edit4);
        pass2ET = (EditText) view.findViewById(R.id.edit5);

        helper = new DatabaseHelper(view.getContext());

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = userET.getText().toString();
                String email = emailET.getText().toString();
                int age = Integer.parseInt(ageET.getText().toString());
                String password = pass1ET.getText().toString();
                String confirmPassword = pass2ET.getText().toString();

                if(user.equals("")||email.equals("")||age==0||password.equals("")||confirmPassword.equals(""))
                    Toast.makeText(view.getContext(), "llena todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    if(password.equals(confirmPassword)){
                        Boolean checkUser = helper.checkUser(user);
                        if(checkUser == false){
                            Boolean insert = helper.insertData(user, email,age, password);
                            if(insert == true){
                                ((MainActivity) getActivity()).setMenuEnabled(true);
                                Toast.makeText(view.getContext(), "INGRESO EXITOSO", Toast.LENGTH_SHORT).show();

                                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                                Principal principal = new Principal();
                                transaction.replace(R.id.fragmentContainer, principal);
                                transaction.addToBackStack(null);
                                transaction.commit();
                            }else{
                                Toast.makeText(view.getContext(), "ingreso fallido", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(view.getContext(), "usario existente, por favor logueate", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(view.getContext(), "contrasena incorrecta", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                Information information = new Information();
                transaction.replace(R.id.fragmentContainer, information);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }


}