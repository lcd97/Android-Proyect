package com.example.libraryapp.bean;

import java.io.Serializable;

public class Category implements Serializable {
    public int Id;
    public String Codigo;
    public String Descripcion;

    public Category() {
        super();
    }

    public Category(int id, String codigo, String descripcion) {
        Id = id;
        Codigo = codigo;
        Descripcion = descripcion;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
