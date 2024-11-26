package com.example.greenzenith;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Adapter extends BaseAdapter {

    Context context;
    String plantName[];
    String plantInfo[];
    int plantImage[];
    int userImage[];
    LayoutInflater inflater;

    public Adapter(Context context, String[] plantName,
                   String[] plantInfo, int plantImage[], int userImage[]) {
        this.context = context;
        this.plantName = plantName;
        this.plantInfo = plantInfo;
        this.plantImage = plantImage;
        this.userImage = userImage;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
         view = inflater.inflate(R.layout.sellers_plants, null);

        TextView text1 = view.findViewById(R.id.plantName);
        TextView text2 = view.findViewById(R.id.plantInfo);
        ImageView image = view.findViewById(R.id.plantImage);

        text1.setText(plantName[i]);
        text2.setText(plantInfo[i]);
        image.setImageResource(plantImage[i]);

        return view;
    }
}
