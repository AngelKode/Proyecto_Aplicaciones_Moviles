package com.example.proyectofinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity
{
    EditText boleta;
    Spinner spinnerTipo;
    Button buttIngresar;

    String idUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharpref2 = getSharedPreferences("Adress",getBaseContext().MODE_PRIVATE);
        SharedPreferences.Editor editor2 = sharpref2.edit();
        editor2.putString("Adress", "192.168.0.10");
        editor2.commit();

        SharedPreferences sharprefIdUsu = this.getSharedPreferences("IdUsuario", this.MODE_PRIVATE);
        String idUs = sharprefIdUsu.getString("IdUsuario", "Null");

        if(idUs != "Null")
        {
            Intent intent = new Intent(this, MenuInicio.class);
            startActivityForResult(intent, 0);
        }
        else
        {

            final List<String> list = new ArrayList<String>();
            list.add("Selecione tipo de usuario");
            list.add("Alumno");
            list.add("Maestro");

            spinnerTipo = (Spinner) findViewById(R.id.spinnerTipoUsuario);

            ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
            adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerTipo.setAdapter(adp1);

            buttIngresar = (Button) findViewById(R.id.inicio);
            buttIngresar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cargarDatos();
                }
            });
        }
    }


    public void cargarDatos()
    {
        String selec = spinnerTipo.getItemAtPosition(spinnerTipo.getSelectedItemPosition()).toString();

        if(selec != "Selecione tipo de usuario")
        {
            SharedPreferences sharpref = this.getSharedPreferences("Adress", this.MODE_PRIVATE);
            String adress = sharpref.getString("Adress", "Null");

            String url = "http://"+adress+"/Proyecto_Final_Web/pages/php/consultas.php";
            consultaUsuario(url,selec);
        }
        else
        {
            Toast.makeText(this, "Ingrese una opcion correcta", Toast.LENGTH_SHORT).show();
        }
    }

    public void consultaUsuario(String URL,String seleccion)
    {
        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    leerJSon(new JSONObject(response));
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
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> parametros = new HashMap<String, String>();
                if(seleccion == "Alumno")
                {
                    parametros.put("op", "9");
                }
                else
                {
                    parametros.put("op", "10");
                }
                boleta = (EditText) findViewById(R.id.editTextClave);
                parametros.put("BC", boleta.getText().toString());
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(request);
    }

    public void leerJSon(JSONObject json) throws JSONException
    {
        JSONArray json_array = json.optJSONArray("usuario");

        if(json_array.getJSONObject(0).getString("idUsuario") != "")
        {
            idUsuario = json_array.getJSONObject(0).getString("idUsuario");
            SharedPreferences sharpref = getSharedPreferences("IdUsuario", getBaseContext().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharpref.edit();
            editor.putString("IdUsuario", idUsuario);
            editor.commit();

            extraerToken();
        }
    }

    public void extraerToken()
    {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>()
                {
                    @Override
                    public void onComplete(@NonNull Task<String> task)
                    {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(MainActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                            return;
                        }
                        // Get new FCM registration token
                        String token = task.getResult();

                        actualizarUsuario(token);
                    }
                });
    }

    public void actualizarUsuario(String token)
    {
        SharedPreferences sharprefAdress = this.getSharedPreferences("Adress", this.MODE_PRIVATE);
        String adress = sharprefAdress.getString("Adress", "Null");
        String URL = "http://"+adress+"/Proyecto_Final_Web/pages/php/actualizar.php";



        StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                if(response.equals("1"))
                {
                    Intent intent = new Intent(getBaseContext(), MenuInicio.class);
                    startActivityForResult(intent, 0);
                }
                else
                {
                    Toast.makeText(getBaseContext(), "Error al actualizar el usuario", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getBaseContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("op", "2");
                parametros.put("token", token);
                parametros.put("ID", idUsuario);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getBaseContext());
        requestQueue.add(request);
    }
}