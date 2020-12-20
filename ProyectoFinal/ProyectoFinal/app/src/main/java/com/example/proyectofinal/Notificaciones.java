package com.example.proyectofinal;

public class Notificaciones {
    private String nombreNotificacion;

    public Notificaciones(String nombre){
        this.nombreNotificacion = nombre;
    }


    public String getNombreContacto() {
        return nombreNotificacion;
    }

    public void setNombreContacto(String nombreContacto) {
        this.nombreNotificacion = nombreContacto;
    }

}
