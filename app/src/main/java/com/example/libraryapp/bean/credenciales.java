package com.example.libraryapp.bean;

import android.content.SharedPreferences;

import java.io.Serializable;

public class credenciales implements Serializable {
    private String Email;
    private String Password;

    //VARIABLE DE PREFERENCIAS
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";

    public credenciales() {
        super();
    }

    public credenciales(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    //METODO PARA ALMACENAR LAS PREFERENCIAS
    public void savePreferences(SharedPreferences preferences){
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(EMAIL, Email);
        editor.putString(PASSWORD, Password);
        editor.apply();
    }

    //METODO PARA OBTENER LAS PREFERENCIAS
    public static credenciales getPreferences(SharedPreferences preferences) {
        credenciales credenciales = new credenciales();

        credenciales.setEmail(preferences.getString(EMAIL,null));
        credenciales.setPassword(preferences.getString(PASSWORD,null));

        return credenciales;
    }

    //METODO PARA ELIMINAR LAS PREFERENCIAS
    public static void deletePreferences(SharedPreferences preferences){
        preferences.edit().clear().commit();
    }
}