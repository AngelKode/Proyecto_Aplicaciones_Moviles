package com.example.proyectofinal;

public class Notificaciones {
    private String nombreNotificacion;
    private String id;
    private String descripcion;
    private String fecha;
    private String Grupo_idGrupo;

    public Notificaciones(String idNotificacion,String titulo,String descripcion, String fecha, String Grupo_idGrupo)
    {
        this.nombreNotificacion = titulo;
        this.id = idNotificacion;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.Grupo_idGrupo = Grupo_idGrupo;
    }

    public Notificaciones(String id,String nombre)
    {
        this.nombreNotificacion = nombre;
        this.id = id;
    }


    public String getNombreContacto()
    {
        return nombreNotificacion;
    }

    public void setNombreContacto(String nombreContacto)
    {
        this.nombreNotificacion = nombreContacto;
    }

    public String getIdNotificacion()
    {
        return id;
    }

    public String getDescripcion()
    {
        return descripcion;
    }


}
