package com.example.proyectofinal.ui.salir;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyectofinal.MainActivity;
import com.example.proyectofinal.MenuInicio;
import com.example.proyectofinal.R;

public class SalirFragment extends Fragment {

    private SalirViewModel salirViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        salirViewModel =
                new ViewModelProvider(this).get(SalirViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        salirViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        SharedPreferences sharpref = root.getContext().getSharedPreferences("IdUsuario", root.getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = sharpref.edit();
        editor.putString("IdUsuario", "Null");
        editor.commit();

        Intent intent = new Intent(root.getContext(), MainActivity.class);
        startActivityForResult(intent, 0);

        return root;
    }
}