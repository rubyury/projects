package com.example.starbell;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AlterData extends Fragment {

    DatabaseHelper helper;
    EditText editUser, editEmail, editPass1, editPass2;
    Button btn1, btn2;
    private String email;

    public AlterData(String email){
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alter_data, container, false);

        editUser = (EditText) view.findViewById(R.id.alter_user);
        editPass1 = (EditText) view.findViewById(R.id.alter_pass);

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = editUser.getText().toString();
                String password = editPass1.getText().toString();

                helper = new DatabaseHelper(view.getContext());
                boolean flag = false;

                if(password.equals("")||user.equals("")){
                    Toast.makeText(view.getContext(), "Llena algun campo", Toast.LENGTH_SHORT).show();
                }
                if (password.equals("")){
                    flag = helper.updateUserName(email, user);
                } else if (user.equals("")) {
                        flag = helper.updateUserPwd(email, password);
                } else {
                    flag = helper.updateUserName(email, user);
                    flag = helper.updateUserPwd(email, password);
                }

                if (flag){
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.replaceFragment(new UserPage(email));
                    Toast.makeText(view.getContext(), "Cambio exitoso", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.replaceFragment(new UserPage(email));
            }
        });
        return view;
    }
}