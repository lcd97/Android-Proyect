package com.example.libraryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.SOAP.sw_SOAP;
import com.example.libraryapp.bean.Customer;
import com.example.libraryapp.bean.Login;

import static android.graphics.Color.WHITE;

public class ProfileActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnPassword;
    String email;
    TextView txtNombre, txtCodigo;
    ImageView img;

    EditText edtOldPass, edtNewPass, edtNewConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Perfil");
        toolbar.setTitleTextColor(WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        btnPassword = findViewById(R.id.contraseña);
        txtCodigo = findViewById(R.id.textView8);
        txtNombre = findViewById(R.id.textView5);
        img = findViewById(R.id.profile_image);

        email = getIntent().getExtras().getString("email");

        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                LayoutInflater inflater = getLayoutInflater();

                builder.setTitle("Cambio de contraseña");

                final View view = inflater.inflate(R.layout.activity_password, null);
                builder.setView(view);
                builder.setCancelable(false);
                builder.setNegativeButton("Cancelar", null);
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //llamar al servicio
                        edtNewPass = view.findViewById(R.id.newPass);
                        edtOldPass = view.findViewById(R.id.oldPass);
                        edtNewConfirm = view.findViewById(R.id.newPassConfirmed);

                        if (!vacio()) {
                            if (!longitud()) {
                                if (edtNewPass.getText().toString() != edtNewConfirm.getText().toString()) {
                                    edtNewConfirm.setError("Contraseña no coincide");
                                    edtNewPass.setError("Contraseña no coincide");
                                } else {
                                    new PassAsyncTask().execute();
                                }
                            }
                        }else{
                            edtNewPass.setError("Debe contener más de 7 dígitos");
                            edtNewConfirm.setError("Debe contener más de 7 dígitos");
                        }
                    }
                });

                final AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        new ProfileAsyncTask().execute();
    }

    //METODO DE VALIDACION DE CAMPOS
    private boolean vacio(){
        boolean band = false;
        if (edtOldPass.getText().toString().length() <= 0 ) {
            edtOldPass.setError("Campo Requerido");
            band = true;
        }

        if (edtNewPass.getText().toString().length() <= 0 ) {
            edtNewPass.setError("Campo Requerido");
            band = true;
        }

        if (edtNewConfirm.getText().toString().length() <= 0 ) {
            edtNewConfirm.setError("Campo Requerido");
            band = true;
        }

        return band;
    }

    private boolean longitud(){
        boolean band = false;

        if (edtNewPass.getText().toString().length() <= 7 || edtNewConfirm.getText().toString().length() <= 7 ) {
            band = true;
        }

        return band;
    }

    private class ProfileAsyncTask extends AsyncTask<Void, Void, Customer> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Customer doInBackground(Void... voids) {
            Customer l = new Customer();
            l = sw_SOAP.CustomerData(email);
            return l;
        }

        @Override
        protected void onPostExecute(Customer l) {
            txtNombre.setText(l.getNames() + " " + l.getLastName());
            txtCodigo.setText(l.getCodigo());

            if(l.getFoto() == null){
                img.setImageResource(R.drawable.user_profile);
            }
        }//FIN ON-POST-EXECUTE
    }//FIN CLASE INTERNA LOGIN

    private class PassAsyncTask extends AsyncTask<Void, Void, Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean){
                Toast.makeText(ProfileActivity.this, "Cambio exitoso", Toast.LENGTH_SHORT).show();
            }
            else
                Toast.makeText(ProfileActivity.this, "Error. Intentelo de nuevo", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return sw_SOAP.PasswordChange(email, edtOldPass.getText().toString(), edtNewPass.getText().toString());
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}