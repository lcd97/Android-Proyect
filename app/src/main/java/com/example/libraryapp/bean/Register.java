package com.example.libraryapp.bean;

import java.io.Serializable;

public class Register implements Serializable {
    public String Codigo;
    public String Email;
    public String Mensaje;
    public boolean Register;

    public Register() {
        super();
    }

    public Register(String codigo, String email, String mensaje, boolean register) {
        Codigo = codigo;
        Email = email;
        Mensaje = mensaje;
        Register = register;
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

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public boolean isRegister() {
        return Register;
    }

    public void setRegister(boolean register) {
        Register = register;
    }
}