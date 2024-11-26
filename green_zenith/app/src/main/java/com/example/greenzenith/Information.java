package com.example.greenzenith;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

public class Information extends Fragment  {

    ImageView img1;
    Button btn1, btn2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_information, container, false);
        img1 = (ImageView) view.findViewById(R.id.img1);
        img1.setImageResource(R.drawable.spiderman);

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                Login login = new Login();
                transaction.replace(R.id.fragmentContainer, login);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                Register register = new Register();
                transaction.replace(R.id.fragmentContainer, register);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });


        return view;
    }

}