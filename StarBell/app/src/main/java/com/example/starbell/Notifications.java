package com.example.starbell;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Notifications extends Fragment{

    ListView list;
    ArrayList<Notification> notifications;
    DatabaseHelper helper;
    public String email;

    public Notifications(String email){
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        list = view.findViewById(R.id.listNoti);

        helper = new DatabaseHelper(view.getContext());
        notifications = helper.getAllNotifications(email);

        AdapterNoti adapter = new AdapterNoti(view.getContext(), notifications);

        list.setAdapter(adapter);

        return view;
    }

}