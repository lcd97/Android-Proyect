package com.example.libraryapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.CollapsibleActionView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.libraryapp.Adapters.BooksAdapter;
import com.example.libraryapp.Adapters.CategoriesAdapter;
import com.example.libraryapp.R;
import com.example.libraryapp.SOAP.sw_SOAP;
import com.example.libraryapp.bean.Book;
import com.example.libraryapp.bean.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class categoriesFragment extends Fragment {

    View rootView;
    RecyclerView recyclerView;
    List<Category> categoryList = new ArrayList<Category>();

    ProgressDialog progressDialog;

    public categoriesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_categories, container, false);

        recyclerView = rootView.findViewById(R.id.recyclerView);
        //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.setMessage("Cargando");
        progressDialog.setCancelable(false);
        progressDialog.show();

        new CategoryListAsyncTask().execute();

        return rootView;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    private class CategoryListAsyncTask extends AsyncTask<Void, Void, List<Category>> {

        @Override
        protected List<Category> doInBackground(Void... voids) {
            categoryList = sw_SOAP.CategoryArrayList();
            return categoryList;
        }

        @Override
        protected void onPostExecute(final List<Category> categories) {
            super.onPostExecute(categories);
            if (categories != null) {
                try {
                    CategoriesAdapter adapter = new CategoriesAdapter(categoryList);
                    adapter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(getContext(), "tocaste " +
                                    categories.get(recyclerView.getChildAdapterPosition(v)).getDescripcion(), Toast.LENGTH_SHORT).show();
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
