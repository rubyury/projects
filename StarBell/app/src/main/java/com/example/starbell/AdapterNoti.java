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

public class AdapterNoti extends BaseAdapter {

    Context context;
    LayoutInflater inflater;
    ArrayList<Notification> notifications;
    DatabaseHelper helper;

    public AdapterNoti(Context context, ArrayList<Notification> notifications) {
        this.context = context;
        this.notifications = notifications;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int i) {
        return notifications.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        if (view == null) {
            view = inflater.inflate(R.layout.notification, viewGroup, false);

            holder = new ViewHolder();

            holder.text1 = view.findViewById(R.id.notiNumber);
            holder.text2 = view.findViewById(R.id.notiName);
            holder.text3 = view.findViewById(R.id.notiDescription);
            holder.text4 = view.findViewById(R.id.notiTime);
            holder.btn1 = view.findViewById(R.id.btn1);
            holder.btn2 = view.findViewById(R.id.btn2);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Notification noti = notifications.get(i);

        holder.text1.setText(String.valueOf(i + 1));
        holder.text2.setText(noti.getName());
        holder.text3.setText(noti.getDescription());
        holder.text4.setText(String.valueOf(noti.getHour())+ " horas | " + (String.valueOf(noti.getMinutes())) +"minutos");

        helper = new DatabaseHelper(view.getContext());
        String name = noti.getName();

        holder.btn2.setOnClickListener(v -> {
            if (helper.deleteNotification(name)){
                Toast.makeText(context, "notificacion eliminada " , Toast.LENGTH_SHORT).show();
                notifications.remove(i);
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "algo a salido mal",  Toast.LENGTH_SHORT).show();
            }

        });

        holder.btn1.setOnClickListener(v -> {
            Toast.makeText(context, "edicion chida",  Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;
        Button btn1;
        Button btn2;
    }
}
