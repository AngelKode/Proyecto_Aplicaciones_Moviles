package com.example.proyectofinal.ui.notificaciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.R;

public class NotificacionFragment extends Fragment {

    private NotificacionViewModel notificacionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificacionViewModel =
                new ViewModelProvider(this).get(NotificacionViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notificacion, container, false);

        return root;
    }
}