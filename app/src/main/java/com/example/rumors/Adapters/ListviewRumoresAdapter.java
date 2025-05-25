package com.example.rumors.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.rumors.Entidades.Rumor;
import com.example.rumors.R;

import java.util.List;

public class ListviewRumoresAdapter extends ArrayAdapter<Rumor> {
    List<Rumor> rumores;
    public ListviewRumoresAdapter(@NonNull Context context, @NonNull List<Rumor> objects) {
        super(context, R.layout.listview_rumores_template, objects);

        this.rumores = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View item = inflater.inflate(R.layout.listview_rumores_template,null);

        TextView lblTitulo = (TextView) item.findViewById(R.id.lblTitulo);
        lblTitulo.setText(rumores.get(position).getTitulo());

        TextView lblDescripcion = (TextView) item.findViewById(R.id.lblDescrpcion);
        lblDescripcion.setText(rumores.get(position).getDescripcion());

        TextView lblCategoria = (TextView) item.findViewById(R.id.lblCategoria);
        lblCategoria.setText(rumores.get(position).getCategoria());

        return item;
    }
}
