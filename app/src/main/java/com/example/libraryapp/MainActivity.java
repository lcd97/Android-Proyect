package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.libraryapp.bean.Credenciales;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    TextView txtRegister;
    EditText edtUsername, edtPassword;
    CheckBox chkRememberMe;
    ImageButton imgFacebook, imgInstagram, imgTwitter;

    //DECLARACION DE CONSTANTE LLAVE PARA LAS PREFERENCIAS
    public static final String KEY_EDITOR = "llave.editor";
    //VARIABLE PARA ALMACENAR LA PREFERENCIA
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.button);
        txtRegister = findViewById(R.id.textView2);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        chkRememberMe = findViewById(R.id.checkBox);
        imgFacebook = findViewById(R.id.imageButton);
        imgInstagram = findViewById(R.id.imageButton2);
        imgTwitter = findViewById(R.id.imageButton3);

        //CREAR LAS PREFERENCIAS
        preferences = getSharedPreferences(KEY_EDITOR, MODE_PRIVATE);

        //OBTENGO LAS PREFERENCIAS
        Credenciales cred = Credenciales.getPreferences(preferences);

        //ASIGNACION DE CAMPOS CON LA ULTIMA PREFERENCIA ALMACENADA
        edtUsername.setText(cred.getEmail());
        edtPassword.setText(cred.getPassword());

        //CHECKEAR CHEKBOX SI EXISTEN DATOS
        if (edtUsername.getText().toString().length() != 0){
            chkRememberMe.setChecked(true);
        }

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, container.class);
                startActivity(i);
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, register.class);
                startActivity(i);
            }
        });

        //EVENTO PARA ALMACENAR O BORRAR LAS CREDENCIALES
        chkRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Credenciales creden = new Credenciales();

                if (buttonView.isChecked()) {
                    //IS CHECKED
                    creden.setEmail(edtUsername.getText().toString());
                    creden.setPassword(edtPassword.getText().toString());

                    creden.savePreferences(preferences);
                }
                else{
                    //NOT CHECKED
                    creden.deletePreferences(preferences);
                }
            }
        });
    }

    //METODO DE VALIDACION DE CAMPOS
    private void validator(){
        if (edtUsername.getText().toString().length() == 0 )
        {

        }

        if (edtPassword.getText().toString().length() == 0 )
        {

        }
    }
}