package com.example.proyectofinal.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.proyectofinal.Notificaciones;
import com.example.proyectofinal.R;

import java.util.ArrayList;

public class MiAdaptador extends BaseAdapter
{
    private Context c;
    private int diseño;
    private ArrayList<Notificaciones> notificaciones;

    public MiAdaptador(Context contexto, int diseño, ArrayList<Notificaciones> notificaciones)
    {
        this.c = contexto;
        this.diseño = diseño;
        this.notificaciones = notificaciones;
    }

    @Override
    public int getCount() {
        return this.notificaciones.size();
    }

    @Override
    public Object getItem(int position) {
        return this.notificaciones.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View vistaDisenio = convertView;

        if (vistaDisenio == null) {
            LayoutInflater inflater = LayoutInflater.from(this.c);
            vistaDisenio = inflater.inflate(this.diseño, null);
        }


        //Ahora el nombre del contacto
        TextView textoNombre = vistaDisenio.findViewById(R.id.nombreNoti);
        textoNombre.setText(this.notificaciones.get(position).getNombreContacto());

        return vistaDisenio;
    }
}