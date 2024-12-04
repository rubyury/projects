package com.example.starbell;

import android.content.Context;
import android.graphics.Color;
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
            holder.text5 = view.findViewById(R.id.notiDays);
            holder.btn2 = view.findViewById(R.id.btn2);
            holder.btn3 = view.findViewById(R.id.btn3);

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

        ArrayList<String> days = helper.getDays(noti.getName());

        StringBuilder builder = new StringBuilder();

        for (String day : days) {
            if (days.isEmpty()) {
                builder.append(" ");
            } else {
                builder.append(day);
                builder.append(" ");
            }
        }

        if (builder.length() > 0) {
            builder.setLength(builder.length() - 2);
        }

        String result = builder.toString();

        holder.text5.setText("Días: " + result);

        holder.btn2.setOnClickListener(v -> {
            if (helper.deleteNotification(name)){
                Toast.makeText(context, "notificacion eliminada " , Toast.LENGTH_SHORT).show();
                notifications.remove(i);
                notifyDataSetChanged();
            } else {
                Toast.makeText(context, "algo a salido mal",  Toast.LENGTH_SHORT).show();
            }

        });

        holder.btn3.setOnClickListener(v -> {

            if (holder.btn3.getText().equals("DESACTIVAR")){
                Toast.makeText(context, "DESACTIVADO",  Toast.LENGTH_SHORT).show();
                holder.btn3.setText("ACTIVAR");
                holder.btn3.setBackgroundColor(Color.RED);
                if (context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) context;

                    mainActivity.cancelNotification(context);
                }
            } else {
                Toast.makeText(context, "ACTIVADO",  Toast.LENGTH_SHORT).show();
                holder.btn3.setText("DESACTIVAR");
                holder.btn3.setBackgroundColor(Color.BLUE);
                if (context instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) context;

                    mainActivity.scheduleNotification(context);
                }
            }

        });

        return view;
    }

    static class ViewHolder {
        TextView text1;
        TextView text2;
        TextView text3;
        TextView text4;
        TextView text5;
        Button btn2;
        Button btn3;
    }
}
