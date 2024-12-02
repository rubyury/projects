package com.example.starbell;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DeleteUsers extends Fragment {

    ListView list;
    ArrayList<User> users;
    DatabaseHelper helper;
    Button btn1;
    public String email;

    public DeleteUsers(String email){
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_users, container, false);

        list = view.findViewById(R.id.listUsers);
        btn1 = view.findViewById(R.id.btn1);

        helper = new DatabaseHelper(view.getContext());
        users = helper.getAllUsers();

        for(int i = 0; i < users.size(); i++){
            String name = users.get(i).getEmail();
            if (name.equals(email)){
                users.remove(i);
            }
        }

        Adapter adapter = new Adapter(view.getContext(), users);

        list.setAdapter(adapter);

        btn1.setOnClickListener(v -> {

            MainActivity mainActivity = (MainActivity) getActivity();
            mainActivity.setMenuEnabled(true);
            mainActivity.replaceFragment(new UserPage(email));

        });

        return view;
    }
}
