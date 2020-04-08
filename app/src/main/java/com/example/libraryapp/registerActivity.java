package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.SOAP.sw_SOAP;
import com.example.libraryapp.bean.Login;
import com.example.libraryapp.bean.Register;

public class registerActivity extends AppCompatActivity {

    public static boolean errored = false;
    public boolean isLogging = false;

    Button btnLogin;
    TextView txtCedula, txtNombre, txtApellido, txtEmail, txtContraseña, txtContraseñaconfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnLogin = findViewById(R.id.button);
        txtCedula = findViewById(R.id.editText);
        txtNombre = findViewById(R.id.editText2);
        txtApellido = findViewById(R.id.editText3);
        txtEmail = findViewById(R.id.editText4);
        txtContraseña = findViewById(R.id.editText5);
        txtContraseñaconfirm = findViewById(R.id.editText6);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RegisterAsyncTask().execute();
            }
        });
    }

    private class RegisterAsyncTask extends AsyncTask<Void, Void, Register> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Register doInBackground(Void... voids) {
            Register l = new Register();
            l = sw_SOAP.reegister(txtEmail.getText().toString(), txtContraseña.getText().toString(), "User", txtCedula.getText().toString(), txtNombre.getText().toString(), txtApellido.getText().toString());
            return l;
        }

        @Override
        protected void onPostExecute(Register l) {
            //SI EL LOGIN ES DIFERENTE DE VACIO
            if (l != null) {
                //SI ESTA LOGGEADO
                if (l.isRegister()) {
                    //MANDARLO AL ACTIVITY PRINCIPAL
                    Intent intent = new Intent(registerActivity.this, containerActivity.class);
                    intent.putExtra("email", txtEmail.getText().toString());
                    startActivity(intent);

                    //MENSAJE DE BIENVENIDA
                    Toast.makeText(registerActivity.this, l.getMensaje(), Toast.LENGTH_SHORT).show();
                } else {
                    //MENSAJE DE ERROR
                    Toast.makeText(registerActivity.this, l.getMensaje(), Toast.LENGTH_SHORT).show();
                    //SI EXISTE ALGUN ERROR EN EL LOGIN SE ELMIMINAN LAS CREDENCIALES
                }
            }else
                Toast.makeText(registerActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();

        }//FIN ON-POST-EXECUTE
    }//FIN CLASE INTERNA LOGIN
}
