package com.example.libraryapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.Adapters.BooksAdapter;
import com.example.libraryapp.MainActivity;
import com.example.libraryapp.ProfileActivity;
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

    ProgressDialog progressDialog;


    ImageView portada;
    TextView txtTitulo, txtAutor, txtDescr;

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

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Cargando");
        progressDialog.setCancelable(false);
        progressDialog.show();

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
        protected void onPostExecute(final List<Book> books) {
            super.onPostExecute(books);
            if (books != null) {
                try {
                    BooksAdapter adapter = new BooksAdapter(books);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            LayoutInflater inflater = getLayoutInflater();

                            builder.setTitle("Detalle Libro");

                            final View view = inflater.inflate(R.layout.item_book, null);

                            txtTitulo = view.findViewById(R.id.textView9);
                            txtAutor = view.findViewById(R.id.textView11);
                            txtDescr = view.findViewById(R.id.textView10);

                            txtAutor.setText(books.get(recyclerView.getChildAdapterPosition(v)).getAutor());
                            txtTitulo.setText(books.get(recyclerView.getChildAdapterPosition(v)).getTitulo());
                            txtDescr.setText(books.get(recyclerView.getChildAdapterPosition(v)).getDescripcion());

                            builder.setView(view);
                            builder.setCancelable(false);
                            builder.setNegativeButton("Cerrar", null);

                            final AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });

                    recyclerView.setAdapter(adapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                progressDialog.dismiss();
            } else {
                progressDialog.dismiss();
                Toast.makeText(rootView.getContext(), "Intentelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
