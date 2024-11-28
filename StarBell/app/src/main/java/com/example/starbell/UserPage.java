package com.example.starbell;

import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserPage extends Fragment {

    DatabaseHelper helper;
    Cursor cursor;
    TextView txt1, txt2;
    public String email;

    public UserPage(String email){
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_user_page, container, false);

        txt1 = (TextView) view.findViewById(R.id.txt1);
        txt2 = (TextView) view.findViewById(R.id.txt2);

        helper = new DatabaseHelper(view.getContext());
        cursor = helper.getUser(email);

        if (cursor.getCount() == 0){
            Toast.makeText(view.getContext(), "no hay datos", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                txt1.setText(""+cursor.getString(0));
                txt2.setText(""+cursor.getString(1));
            }
        }
        return view;
    }
}