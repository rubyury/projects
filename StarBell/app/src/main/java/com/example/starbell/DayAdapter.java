package com.example.starbell;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import java.util.ArrayList;

public class DayAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<DayItem> dayList;

    public DayAdapter(Context context, ArrayList<DayItem> dayList) {
        this.context = context;
        this.dayList = dayList;
    }

    @Override
    public int getCount() {
        return dayList.size();
    }

    @Override
    public Object getItem(int position) {
        return dayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.day, parent, false);
            holder = new ViewHolder();
            holder.checkBox = convertView.findViewById(R.id.checkBoxDay);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DayItem dayItem = dayList.get(position);
        holder.checkBox.setText(dayItem.getDayName());
        holder.checkBox.setChecked(dayItem.isChecked());

        holder.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> dayItem.setChecked(isChecked));

        return convertView;
    }

    static class ViewHolder {
        CheckBox checkBox;
    }

}
