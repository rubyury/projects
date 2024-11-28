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

                String email = edit1.getText().toString();
                String password = edit2.getText().toString();

                if(email.equals("")||password.equals(""))
                    Toast.makeText(view.getContext(), "llena todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkCredentials =  helper.checkUserPassword(email, password);
                    if(checkCredentials == true){
                        Toast.makeText(view.getContext(), "EXITO", Toast.LENGTH_SHORT).show();
                        MainActivity mainActivity = (MainActivity) getActivity();
                        mainActivity.replaceFragment(new UserPage());
                    }else{
                        Toast.makeText(view.getContext(), "datos invalidos", Toast.LENGTH_SHORT).show();
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