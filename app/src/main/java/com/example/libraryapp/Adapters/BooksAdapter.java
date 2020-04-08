package com.example.libraryapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.libraryapp.R;
import com.example.libraryapp.bean.Book;

import java.util.List;
import java.util.Random;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.ViewHolderBooks> implements View.OnClickListener {
    private List<Book> list;
    private View.OnClickListener listener;

    public BooksAdapter(List<Book> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolderBooks onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_book, parent, false);

        v.setOnClickListener(this);

        return new ViewHolderBooks(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBooks holder, final int position) {
        holder.asignarDatos(list.get(position));
        holder.btnShared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(v.getContext(), "Compartir " + list.get(position).getTitulo(), Toast.LENGTH_SHORT).show();
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Me gusta el libro " +
                        list.get(position).getTitulo() + " del autor " + list.get(position).getAutor() +
                        "\nLo encuentras en FoxBook. wwww.google.com/");
                sendIntent.setType("text/plain");
                v.getContext().startActivity(sendIntent);
            }
        });
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
            try
            {
                byte[] myImage = book.getPortada();

                Bitmap bmp=BitmapFactory.decodeByteArray(myImage,0,myImage.length);
                if (bmp!=null)
                {
                    img.setImageBitmap(bmp);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
