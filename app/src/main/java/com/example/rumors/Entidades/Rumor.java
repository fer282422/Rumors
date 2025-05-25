package com.example.rumors.Entidades;

import android.os.Bundle;

import java.util.List;

public class Rumor {
    private String Titulo;
    private String Descripcion;
    private String Categoria;
    private String Tipo;
    private List<String> Detalles;

    public Rumor(String titulo, String descripcion, String categoria, String tipo, List<String> detalles) {
        Titulo = titulo;
        Descripcion = descripcion;
        Categoria = categoria;
        Tipo = tipo;
        Detalles = detalles;
    }

    public Rumor(Bundle b){
        Titulo = b.getString("titulo");
        Descripcion = b.getString("descripcion");
        Categoria = b.getString("categoria");
        Tipo = b.getString("tipo");
        //Detalles = b.getString("titulo");
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public List<String> getDetalles() {
        return Detalles;
    }

    public void setDetalles(List<String> detalles) {
        Detalles = detalles;
    }

    public Bundle toBundle(){
        Bundle b = new Bundle();

        b.putString("titulo",this.getTitulo());
        b.putString("descripcion",this.getDescripcion());
        b.putString("categoria",this.getCategoria());
        b.putString("tipo",this.getTipo());
        //b.putString("detalles",this.getTipo());

        return b;
    }
}
