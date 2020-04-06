package com.example.libraryapp.bean;

import java.io.Serializable;
import java.util.Date;

public class Rental implements Serializable {
    public int Id;
    public String Codigo;
    public Date FechaAlquiler;
    public Date FechaDevolucion;
    public Date FechaRealDevolucion;
    public int ClienteId;

    public Rental() {
        super();
    }

    public Rental(int id, String codigo, Date fechaAlquiler, Date fechaDevolucion, Date fechaRealDevolucion, int clienteId) {
        Id = id;
        Codigo = codigo;
        FechaAlquiler = fechaAlquiler;
        FechaDevolucion = fechaDevolucion;
        FechaRealDevolucion = fechaRealDevolucion;
        ClienteId = clienteId;
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

    public Date getFechaAlquiler() {
        return FechaAlquiler;
    }

    public void setFechaAlquiler(Date fechaAlquiler) {
        FechaAlquiler = fechaAlquiler;
    }

    public Date getFechaDevolucion() {
        return FechaDevolucion;
    }

    public void setFechaDevolucion(Date fechaDevolucion) {
        FechaDevolucion = fechaDevolucion;
    }

    public Date getFechaRealDevolucion() {
        return FechaRealDevolucion;
    }

    public void setFechaRealDevolucion(Date fechaRealDevolucion) {
        FechaRealDevolucion = fechaRealDevolucion;
    }

    public int getClienteId() {
        return ClienteId;
    }

    public void setClienteId(int clienteId) {
        ClienteId = clienteId;
    }
}
