package com.example.proyectofinal.ui.home;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinal.Notificaciones;
import com.example.proyectofinal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class HomeFragment extends Fragment
{
    TextView titulo, des;
    Button buttActua;
    String idUsu;

    private HomeViewModel homeViewModel;
    ArrayList<Notificaciones> notificaciones = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState)
    {
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferences sharpref2 = root.getContext().getSharedPreferences("IdUsuario", root.getContext().MODE_PRIVATE);
        String idUs = sharpref2.getString("IdUsuario", "Null");

        idUsu = idUs;

        SharedPreferences sharpref = root.getContext().getSharedPreferences("Adress", root.getContext().MODE_PRIVATE);
        String adress = sharpref.getString("Adress", "Null");

        String url = "http://"+adress+"/Proyecto_Final_Web/pages/php/consultas.php";

        cargarNotificaciones(url,root);

        //Lista de usuarios con su escuchador
        final ListView lista = root.findViewById(R.id.listView);
        MiAdaptador adaptador = new MiAdaptador(root.getContext(), R.layout.disenio_not,notificaciones);
        lista.setAdapter(adaptador);
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                TableRow tableRowLista = root.findViewById(R.id.rowLista);
                tableRowLista.setVisibility(view.GONE);

                TableRow tableRowNotificacion = root.findViewById(R.id.rowNotificacion);
                tableRowNotificacion.setVisibility(view.VISIBLE);

                cargarDatosNotificacion(adaptador.regresarId(position),root);
            }
        });

        return root;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)
    {
        TableRow tableRowLista = getView().findViewById(R.id.rowLista);
        tableRowLista.setVisibility(getView().VISIBLE);

        TableRow tableRowNotificacion = getView().findViewById(R.id.rowNotificacion);
        tableRowNotificacion.setVisibility(getView().GONE);

        SharedPreferences sharpref = getContext().getSharedPreferences("Adress", getContext().MODE_PRIVATE);
        String adress = sharpref.getString("Adress", "Null");

        String url = "http://"+adress+"/Proyecto_Final_Web/pages/php/consultas.php";

        cargarNotificaciones(url,getView());
        return super.onOptionsItemSelected(item);
    }

    //*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-*LISTA*-
    public void cargarNotificaciones(String URL, View root)
    {
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    leerJSon(new JSONObject(response),root);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(root.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("op", "8");
                parametros.put("ID", idUsu);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
        requestQueue.add(request);
    }

    public void leerJSon(JSONObject json, View root) throws JSONException
    {
        JSONArray json_array = json.optJSONArray("notificacion");

        for (int i = 0; i < json_array.length(); i++)
        {
            Notificaciones aux = new Notificaciones(
                    json_array.getJSONObject(i).getString("idNotificacion"),
                    json_array.getJSONObject(i).getString("titulo")
            );
            notificaciones.add(aux);
        }
    }

    //*NOTIFICACION*-*NOTIFICACION*-*NOTIFICACION*-*NOTIFICACION*-*NOTIFICACION*-*NOTIFICACION*-*NOTIFICACION*-*NOTIFICACION*-
    //Cargar datos de notificacion seleccionada
    public void cargarDatosNotificacion(String idNotificacion,View root)
    {
        SharedPreferences sharpref = root.getContext().getSharedPreferences("Adress", root.getContext().MODE_PRIVATE);
        String adress = sharpref.getString("Adress", "Null");

        String url ="http://"+adress+"/Proyecto_Final_Web/pages/php/consultas.php";
        cargarNotificacion(url, root, idNotificacion);
    }

    public void cargarNotificacion(String URL, View root, String idNotificacion)
    {

        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    leerJSonNotificacion(new JSONObject(response),root);
                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(root.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("op", "7");
                parametros.put("ID", idNotificacion);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(root.getContext());
        requestQueue.add(request);
    }

    public void leerJSonNotificacion(JSONObject json, View root) throws JSONException
    {
        titulo = root.findViewById(R.id.textViewTitulo);
        des = root.findViewById(R.id.textViewDescrip);
        buttActua = root.findViewById(R.id.buttonRegresar);

        JSONArray json_array = json.optJSONArray("notificacion");

        Notificaciones aux = new Notificaciones(
                json_array.getJSONObject(0).getString("idNotificacion"),
                json_array.getJSONObject(0).getString("titulo"),
                json_array.getJSONObject(0).getString("descripcion"),
                json_array.getJSONObject(0).getString("fecha"),
                json_array.getJSONObject(0).getString("Grupo_idGrupo")
        );

        titulo.setText(aux.getNombreContacto());
        des.setText(aux.getDescripcion());

        buttActua.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TableRow tableRowLista = root.findViewById(R.id.rowLista);
                tableRowLista.setVisibility(getView().VISIBLE);

                TableRow tableRowNotificacion = root.findViewById(R.id.rowNotificacion);
                tableRowNotificacion.setVisibility(getView().GONE);
            }
        });
    }
}