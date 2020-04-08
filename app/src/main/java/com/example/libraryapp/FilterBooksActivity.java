package com.example.libraryapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.libraryapp.Adapters.BooksAdapter;
import com.example.libraryapp.Fragments.bookListFragment;
import com.example.libraryapp.SOAP.sw_SOAP;
import com.example.libraryapp.bean.Book;

import java.util.ArrayList;
import java.util.List;

import static android.graphics.Color.WHITE;

public class FilterBooksActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<Book> books = new ArrayList<Book>();
    ProgressDialog progressDialog;


    ImageView portada;
    TextView txtTitulo, txtAutor, txtDescr;

    Toolbar toolbar;

    int materia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter_books);

        toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Libros Filtrados");
        toolbar.setTitleTextColor(WHITE);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back);

        recyclerView = findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(FilterBooksActivity.this, 3);
        recyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(FilterBooksActivity.this);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Cargando");
        progressDialog.setCancelable(false);
        progressDialog.show();

        materia = getIntent().getIntExtra("categoriaId", 0);

        //LLAMAR A LA TAREA DE LLENAR LA LISTA DE LIBRO
        new BookListFilterAsyncTask().execute();
    }

    private class BookListFilterAsyncTask extends AsyncTask<Void, Void, List<Book>> {


        @Override
        protected List<Book> doInBackground(Void... voids) {
            books = sw_SOAP.bookArrayListFilter(materia);

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
                            final AlertDialog.Builder builder = new AlertDialog.Builder(FilterBooksActivity.this);
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
                Toast.makeText(FilterBooksActivity.this, "Intentelo de nuevo", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}