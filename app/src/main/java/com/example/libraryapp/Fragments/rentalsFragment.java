package com.example.libraryapp.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.libraryapp.R;
import com.example.libraryapp.bean.Book;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class rentalsFragment extends Fragment {

    TextView rentas;
    View rootView;

    public rentalsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_rentals, container, false);

        rentas = rootView.findViewById(R.id.textView6);
        new RentalAsyncTask().execute();

        return rootView;
    }


    private class RentalAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            rentas.setText("No posee alquileres pendientes");
        }
    }
}
