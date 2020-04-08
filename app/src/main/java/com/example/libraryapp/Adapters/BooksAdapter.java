package com.example.libraryapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.R;
import com.example.libraryapp.bean.Book;

import java.util.List;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolderBooks> {
    private List<Book> list;

    public BooksAdapter(List<Book> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderBooks onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_book, parent, false);
        return new ViewHolderBooks(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBooks holder, int position) {
        holder.asignarDatos(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolderBooks extends RecyclerView.ViewHolder {

        ImageView img;
        Button btnShared;
        TextView txtTitle;

        public ViewHolderBooks(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView2);
            btnShared = itemView.findViewById(R.id.button2);
            txtTitle = itemView.findViewById(R.id.textView3);
        }

        public void asignarDatos(Book book) {
            txtTitle.setText(book.getTitulo());
            byte[] blob = book.getPortada();
            Bitmap bmp= BitmapFactory.decodeByteArray(blob,0,blob.length);
            img.setImageBitmap(bmp);
        }
    }
}
