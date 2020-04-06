package com.example.libraryapp.bean;

import java.io.Serializable;
import java.util.Date;

public class Book implements Serializable {
    public int Id;
    public String Codigo;
    public String Titulo;
    public String ISBN;
    public String Autor;
    public byte[] Portada;
    public Date Adquisicion;
    public String Descripcion;
    public int NumeroCopia;

    public Book() {
        super();
    }

    public Book(int id, String codigo, String titulo, String ISBN, String autor, byte[] portada, Date adquisicion, String descripcion, int numeroCopia) {
        Id = id;
        Codigo = codigo;
        Titulo = titulo;
        this.ISBN = ISBN;
        Autor = autor;
        Portada = portada;
        Adquisicion = adquisicion;
        Descripcion = descripcion;
        NumeroCopia = numeroCopia;
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

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAutor() {
        return Autor;
    }

    public void setAutor(String autor) {
        Autor = autor;
    }

    public byte[] getPortada() {
        return Portada;
    }

    public void setPortada(byte[] portada) {
        Portada = portada;
    }

    public Date getAdquisicion() {
        return Adquisicion;
    }

    public void setAdquisicion(Date adquisicion) {
        Adquisicion = adquisicion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public int getNumeroCopia() {
        return NumeroCopia;
    }

    public void setNumeroCopia(int numeroCopia) {
        NumeroCopia = numeroCopia;
    }
}
