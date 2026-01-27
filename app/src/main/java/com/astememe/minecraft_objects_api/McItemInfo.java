package com.astememe.minecraft_objects_api;

import android.widget.ImageView;

public class McItemInfo {
    private String titulo;
    private String descripcion;
    private String imagen;

    public McItemInfo(String titulo, String descripcion, String enlaceImagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = enlaceImagen;

    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }
}
