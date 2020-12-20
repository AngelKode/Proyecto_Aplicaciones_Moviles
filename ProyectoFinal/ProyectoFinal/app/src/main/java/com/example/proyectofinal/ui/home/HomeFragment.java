package com.example.proyectofinal.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.Notificaciones;
import com.example.proyectofinal.R;

import java.util.ArrayList;


public class HomeFragment extends Fragment
{

    private HomeViewModel homeViewModel;
    ArrayList<Notificaciones> notificaciones = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView lista = root.findViewById(R.id.listView);
        generarContactos();

        MiAdaptador adaptador = new MiAdaptador(root.getContext(), R.layout.disenio_not,notificaciones);

        lista.setAdapter(adaptador);
        return root;
    }

    public void generarContactos(){

        Notificaciones aux = new Notificaciones("BECAS 2010");
        notificaciones.add(aux);
        aux = new Notificaciones("BECAS BEIFI");
        notificaciones.add(aux);
        aux = new Notificaciones("BECAS Delfin");
        notificaciones.add(aux);
        aux = new Notificaciones("Servicio Social");
        notificaciones.add(aux);
        aux = new Notificaciones("Inscripciones");
        notificaciones.add(aux);
    }
}