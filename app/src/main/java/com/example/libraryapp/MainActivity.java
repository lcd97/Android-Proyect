package com.example.libraryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.SOAP.sw_SOAP;
import com.example.libraryapp.bean.Credenciales;
import com.example.libraryapp.bean.Login;

import static android.graphics.Color.WHITE;

public class MainActivity extends AppCompatActivity {

    public static boolean errored = false;
    public boolean isLogging = false;

    public Button btnLogin;
    public TextView txtRegister;
    public EditText edtUsername, edtPassword;
    public CheckBox chkRememberMe;
    public ImageButton imgFacebook, imgInstagram, imgTwitter;

    ProgressBar webservicePG;

    //DECLARACION DE CONSTANTE LLAVE PARA LAS PREFERENCIAS
    public static final String KEY_EDITOR = "llave.editor";
    //VARIABLE PARA ALMACENAR LA PREFERENCIA
    private SharedPreferences preferences;

    private Credenciales creden = new Credenciales();

    Toolbar toolbar;

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

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Biblioteca");
        toolbar.setTitleTextColor(WHITE);

        //CREAR LAS PREFERENCIAS
        preferences = getSharedPreferences(KEY_EDITOR, MODE_PRIVATE);

        //OBTENGO LAS PREFERENCIAS
        final Credenciales cred = Credenciales.getPreferences(preferences);

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
               if (!vacio()) {
                   //LLAMAR LA TAREA
                   new loginAsyncTask().execute();
               }else{
                   new AlertDialog.Builder(MainActivity.this)
                           .setTitle("Error")
                           .setMessage("Campos requeridos no pueden quedar vacíos")
                           .setPositiveButton("Reintentar", null)
                           .setCancelable(false)
                           .show();
               }
            }
        });

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, register.class);
                startActivity(i);
            }
        });

        chkRememberMe.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    //IS CHECKED
                    creden.setEmail(edtUsername.getText().toString());
                    creden.setPassword(edtPassword.getText().toString());

                    creden.savePreferences(preferences);
                } else {
                    //NOT CHECKED
                    creden.deletePreferences(preferences);
                }
            }
        });
    }

    //METODO DE VALIDACION DE CAMPOS
    private boolean vacio(){
        boolean band = false;
        if (edtUsername.getText().toString().length() <= 0 ) {
            edtUsername.setError("Campo Requerido");
            band = true;
        }

        if (edtPassword.getText().toString().length() <= 0 ) {
            edtPassword.setError("Campo Requerido");
            band = true;
        }
        return band;
    }

    private class loginAsyncTask extends AsyncTask<Void, Void, Login> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Login doInBackground(Void... voids) {
            Login l = new Login();
            l = sw_SOAP.login(edtUsername.getText().toString(), edtPassword.getText().toString());
            return l;
        }

        @Override
        protected void onPostExecute(Login l) {
            //SI EL LOGIN ES DIFERENTE DE VACIO
            if (l != null) {
                //SI ESTA LOGGEADO
                if (l.isLogged()) {

                    //MANDARLO AL ACTIVITY PRINCIPAL
                    startActivity(new Intent(MainActivity.this, container.class));

                    //MENSAJE DE BIENVENIDA
                    Toast.makeText(MainActivity.this, l.getMensaje(), Toast.LENGTH_SHORT).show();
                } else {
                    //MENSAJE DE ERROR
                    Toast.makeText(MainActivity.this, l.getMensaje(), Toast.LENGTH_SHORT).show();
                    //SI EXISTE ALGUN ERROR EN EL LOGIN SE ELMIMINAN LAS CREDENCIALES
                    creden.deletePreferences(preferences);
                }
            }else
                Toast.makeText(MainActivity.this, "Error en el servicio", Toast.LENGTH_SHORT).show();

        }//FIN ON-POST-EXECUTE
    }//FIN CLASE INTERNA LOGIN
}//FIN MAIN