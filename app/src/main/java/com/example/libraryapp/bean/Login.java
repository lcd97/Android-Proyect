package com.example.libraryapp.bean;

public class Login {
    public String UserId;
    public String UserName;
    public String Email;
    public String Rol;
    public byte[] Imagen;
    public String Mensaje;
    public boolean IsLogged;

    public Login() {
        super();
    }

    public Login(String userId, String userName, String email, String rol, byte[] imagen, String mensaje, boolean isLogged) {
        UserId = userId;
        UserName = userName;
        Email = email;
        Rol = rol;
        Imagen = imagen;
        Mensaje = mensaje;
        IsLogged = isLogged;
    }

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getRol() {
        return Rol;
    }

    public void setRol(String rol) {
        Rol = rol;
    }

    public byte[] getImagen() {
        return Imagen;
    }

    public void setImagen(byte[] imagen) {
        Imagen = imagen;
    }

    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String mensaje) {
        Mensaje = mensaje;
    }

    public boolean isLogged() {
        return IsLogged;
    }

    public void setLogged(boolean logged) {
        IsLogged = logged;
    }
}
