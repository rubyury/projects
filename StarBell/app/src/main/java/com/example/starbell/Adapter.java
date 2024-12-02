package com.example.starbell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<User> users;
    DatabaseHelper helper;

    public Adapter(Context context, ArrayList<User> users) {
        this.context = context;
        this.users = users;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return users.size();
    }

    @Override
    public Object getItem(int i) {
        return users.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.users, viewGroup, false);

            holder = new ViewHolder();
            holder.text1 = view.findViewById(R.id.userNumber);
            holder.text2 = view.findViewById(R.id.userName);
            holder.text3 = view.findViewById(R.id.userEmail);
            holder.text4 = view.findViewById(R.id.userPassword);
            holder.btnAction = view.findViewById(R.id.btn1);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        User user = users.get(i);

        holder.text1.setText(String.valueOf(i + 1));
        holder.text2.setText(user.getName());
        holder.text3.setText(user.getEmail());
        holder.text4.setText(user.getPassword());

        helper = new DatabaseHelper(view.getContext());
        String email = user.getEmail();

        holder.btnAction.setOnClickListener(v -> {

            if (helper.deleteUser(email)){
                Toast.makeText(context, "Usuario eliminado: " + user.getName(), Toast.LENGTH_SHORT).show();
                users.remove(i);
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "algo a salido mal",  Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;
        Button btnAction;
    }
}
