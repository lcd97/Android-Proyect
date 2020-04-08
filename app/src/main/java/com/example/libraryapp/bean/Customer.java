package com.example.libraryapp.bean;

import java.io.Serializable;

public class Customer implements Serializable {
    public int Id;
    public String Names;
    public String LastName;
    public String Codigo;
    public String Email;
    public byte[] Foto;

    public Customer() {
        super();
    }

    public Customer(int id, String names, String lastName, String codigo, String email, byte[] foto) {
        Id = id;
        Names = names;
        LastName = lastName;
        Codigo = codigo;
        Email = email;
        Foto = foto;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNames() {
        return Names;
    }

    public void setNames(String names) {
        Names = names;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public byte[] getFoto() {
        return Foto;
    }

    public void setFoto(byte[] foto) {
        Foto = foto;
    }
}
