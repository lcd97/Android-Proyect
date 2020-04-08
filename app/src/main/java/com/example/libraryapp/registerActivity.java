package com.example.libraryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
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

import static android.graphics.Color.WHITE;

public class registerActivity extends AppCompatActivity {

    public static boolean errored = false;
    public boolean isLogging = false;

    Button btnLogin;
    TextView txtCedula, txtNombre, txtApellido, txtEmail, txtContraseña, txtContraseñaconfirm;

    Toolbar toolbar;
    ProgressDialog progressDialog;

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

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Registro");
        toolbar.setTitleTextColor(WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog(registerActivity.this);
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.setMessage("Cargando");
                progressDialog.setCancelable(false);
                progressDialog.show();

                if(!vacio()) {
                    if (txtCedula.getText().toString().length() == 14) {
                        new RegisterAsyncTask().execute();
                    } else {
                        progressDialog.dismiss();
                        txtCedula.setError("La cantidad mínima es de 14 dígitos");
                    }
                }
                else
                    progressDialog.dismiss();
            }
        });
    }

    public boolean vacio(){
        boolean band = false;
        if(txtNombre.getText().toString().length() <= 0){
            txtNombre.setError("Campo requerido");
            band = true;
        }

        if(txtApellido.getText().toString().length() <= 0){
            txtApellido.setError("Campo requerido");
            band = true;
        }

        if(txtEmail.getText().toString().length() <= 0){
            txtEmail.setError("Campo requerido");
            band = true;
        }

        if(txtContraseña.getText().toString().length() <= 0){
            txtContraseña.setError("Campo requerido");
            band = true;
        }

        if(txtContraseñaconfirm.getText().toString().length() <= 0){
            txtContraseñaconfirm.setError("Campo requerido");
            band = true;
        }
            return band;
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
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

            progressDialog.dismiss();

        }//FIN ON-POST-EXECUTE
    }//FIN CLASE INTERNA LOGIN
}
