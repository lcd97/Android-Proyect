package com.example.libraryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.libraryapp.Fragments.bookListFragment;
import com.example.libraryapp.Fragments.categoriesFragment;
import com.example.libraryapp.Fragments.rentalsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class container extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        BottomNavigationView bottonNav = findViewById(R.id.bottom_nav);
        bottonNav.setOnNavigationItemSelectedListener(navListener);

        //MANDAR POR DEFECTO EL FRAGMENT PRINCIPAL DE LA APP
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
               new bookListFragment()).commit();
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
}