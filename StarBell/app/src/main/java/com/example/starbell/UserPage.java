package com.example.starbell;

import android.database.Cursor;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class UserPage extends Fragment {

    DatabaseHelper helper;
    Cursor cursor;
    TextView txt1, txt2, txt3;
    public String email;
    Button btn1, btn2, btn3, btn4;

    public UserPage(String email){
        this.email = email;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_user_page, container, false);

        txt1 = (TextView) view.findViewById(R.id.txt1);
        txt2 = (TextView) view.findViewById(R.id.txt2);
        txt3 = (TextView) view.findViewById(R.id.txt3);

        helper = new DatabaseHelper(view.getContext());
        cursor = helper.getUser(email);

        if (cursor.getCount() == 0){
            Toast.makeText(view.getContext(), "no hay datos", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()){
                txt1.setText(""+cursor.getString(0));
                txt2.setText(""+cursor.getString(1));
                txt3.setText(""+cursor.getString(2));
            }
        }

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.replaceFragment(new AlterData(email));
            }
        });

        btn2 = (Button) view.findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setMenuEnabled(false);
                mainActivity.replaceFragment(new DeleteUsers(email));
            }
        });

        btn3 = (Button) view.findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (helper.deleteUser(email)){
                    Toast.makeText(view.getContext(), "Usuario eliminado", Toast.LENGTH_SHORT).show();
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.setMenuEnabled(false);
                    mainActivity.setEmail("");
                    mainActivity.replaceFragment(new Principal());
                } else {
                    Toast.makeText(view.getContext(), "algo a salido mal",  Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn4 = (Button) view.findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.setMenuEnabled(false);
                mainActivity.setEmail("");
                mainActivity.replaceFragment(new Principal());
            }
        });

        return view;
    }
}