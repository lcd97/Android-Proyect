package com.example.libraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.libraryapp.Fragments.bookListFragment;
import com.example.libraryapp.Fragments.categoriesFragment;
import com.example.libraryapp.Fragments.rentalsFragment;
import com.example.libraryapp.bean.Credenciales;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class containerActivity extends AppCompatActivity {

    public Toolbar toolbar;
    public String email;

    //DECLARACION DE CONSTANTE LLAVE PARA LAS PREFERENCIAS
    public static final String KEY_EDITOR = "llave.editor";
    //VARIABLE PARA ALMACENAR LA PREFERENCIA
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Principal");

        BottomNavigationView bottonNav = findViewById(R.id.bottom_nav);
        bottonNav.setOnNavigationItemSelectedListener(navListener);

        //MANDAR POR DEFECTO EL FRAGMENT PRINCIPAL DE LA APP
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
               new bookListFragment()).commit();

        //CREAR LAS PREFERENCIAS
        preferences = getSharedPreferences(KEY_EDITOR, MODE_PRIVATE);

        email = getIntent().getExtras().getString("email");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_options, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.profile:
                Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
                intent.putExtra("email", email);
                startActivity(intent);
                break;

            case R.id.signOut:
                //BORRAR LAS CREDENCIALES DE PASSWORD DEL LOGIN
                Credenciales credenciales = new Credenciales();
                credenciales.deletePassword(preferences);

                startActivity(new Intent(getBaseContext(), MainActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()){
                        case R.id.nav_booklist:
                            selectedFragment = new bookListFragment();
                            break;
                        case R.id.nav_category:
                            selectedFragment = new categoriesFragment();
                            break;
                        case R.id.nav_rentals:
                            selectedFragment = new rentalsFragment();
                            break;
                    }

                    //MOSTAR EL FRAGMENT SELECCIONADO
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };
}//FIN DE LA CLASE