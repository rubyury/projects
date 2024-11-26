package com.example.greenzenith;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class Principal extends Fragment implements AdapterView.OnItemClickListener {

    ListView list;

    //se deberian sacar directamente de la base de datos en la pagina principal
    //los usuarios ya registrados y sus plantas de forma aleatoria con otra clase
    //pero no estoy seguro de que base de usara

    String plantName[] = {"nombre1", "nombre2"};
    String plantInfo[] = {"planta1", "planta2"};
    int plantImage[] = {R.drawable.fungi, R.drawable.spiderman};
    int userImage[];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.fragment_principal, container, false);

        list = view.findViewById(R.id.list);
        list.setOnItemClickListener(this);

        Adapter adapter = new Adapter(view.getContext(), plantName, plantInfo, plantImage, userImage);

        list.setAdapter(adapter);

        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String text = Integer.toString(i);

        switch (i) {
            case 0:
                Toast.makeText(view.getContext(), "hola", Toast.LENGTH_SHORT).show();
                break;
            default:

                break;
        }
    }
}