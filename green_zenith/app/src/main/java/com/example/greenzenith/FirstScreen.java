package com.example.greenzenith;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FirstScreen extends Fragment {

    Button btn1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_first_screen, container, false);

        btn1 = (Button) view.findViewById(R.id.homePage);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                Information information = new Information();
                transaction.replace(R.id.fragmentContainer, information);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        ((MainActivity) getActivity()).setMenuEnabled(false);

        return view;
    }
}