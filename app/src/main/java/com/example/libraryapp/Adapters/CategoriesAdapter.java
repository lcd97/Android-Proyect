package com.example.libraryapp.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.R;
import com.example.libraryapp.bean.Book;
import com.example.libraryapp.bean.Category;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolderCategories> implements View.OnClickListener{
    public CategoriesAdapter(List<Category> list) {
        this.list = list;
    }

    private List<Category> list;
    private View.OnClickListener listener;

    @NonNull
    @Override
    public ViewHolderCategories onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_categories, parent, false);

        v.setOnClickListener(this);

        return new ViewHolderCategories(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolderCategories holder, int position) {
        holder.asignarDatos(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolderCategories extends RecyclerView.ViewHolder {
        TextView txtCategoria;

        public ViewHolderCategories(@NonNull View itemView) {
            super(itemView);
            txtCategoria = itemView.findViewById(R.id.textView7);
        }

        public void asignarDatos(Category category) {
            txtCategoria.setText(category.getDescripcion());
        }
    }
}
