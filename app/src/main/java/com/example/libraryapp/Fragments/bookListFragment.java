package com.example.libraryapp.Fragments;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.libraryapp.Adapters.BooksAdapter;
import com.example.libraryapp.R;
import com.example.libraryapp.SOAP.sw_SOAP;
import com.example.libraryapp.bean.Book;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class bookListFragment extends Fragment {

    View rootView;
    RecyclerView recyclerView;
    List<Book> books = new ArrayList<Book>();

    public bookListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_book_list, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(gridLayoutManager);



        //LLAMAR A LA TAREA DE LLENAR LA LISTA DE LIBRO
        new BookListAsyncTask().execute();

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private class BookListAsyncTask extends AsyncTask<Void, Void, List<Book>> {


        @Override
        protected List<Book> doInBackground(Void... voids){
            books = sw_SOAP.bookArrayList();

            return books;
        }

        @Override
        protected void onPostExecute(List<Book> books) {
            super.onPostExecute(books);
            if(books != null){
                try {
                    BooksAdapter adapter = new BooksAdapter(books);
                    recyclerView.setAdapter(adapter);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }else
                Toast.makeText(rootView.getContext(), "Intentelo de nuevo", Toast.LENGTH_SHORT).show();
        }
    }
}
