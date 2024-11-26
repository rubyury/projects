package com.example.greenzenith;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Login extends Fragment {

    EditText edit1, edit2;
    Button btn1, btn2;
    DatabaseHelper helper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_login, container, false);
        edit1 = (EditText) view.findViewById(R.id.edit1);
        edit2 = (EditText) view.findViewById(R.id.edit2);

        helper = new DatabaseHelper(view.getContext());

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = edit1.getText().toString();
                String password = edit2.getText().toString();
                if(user.equals("")||password.equals(""))
                    Toast.makeText(view.getContext(), "llena todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials =  helper.checkUserPassword(user, password);
                    if(checkCredentials == true){
                        ((MainActivity) getActivity()).setMenuEnabled(true);
                        Toast.makeText(view.getContext(), "EXITO", Toast.LENGTH_SHORT).show();
                        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                        Principal principal = new Principal();
                        transaction.replace(R.id.fragmentContainer, principal);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }else{
                        Toast.makeText(view.getContext(), "datos invaliodos", Toast.LENGTH_SHORT).show();
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