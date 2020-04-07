package com.example.libraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Toast;

import com.example.libraryapp.Fragments.bookListFragment;
import com.example.libraryapp.Fragments.categoriesFragment;
import com.example.libraryapp.Fragments.rentalsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class container extends AppCompatActivity {

    public Toolbar toolbar;

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
                Toast.makeText(getBaseContext(), "ACTIVIDAD PERFIL", Toast.LENGTH_SHORT).show();
                break;

            case R.id.signOut:
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