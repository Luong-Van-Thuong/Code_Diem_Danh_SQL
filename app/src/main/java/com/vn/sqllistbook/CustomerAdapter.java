package com.vn.sqllistbook;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.MyViewHolder> {

    private Context context;
    private int position;
//    private ArrayList book_id, book_title, book_author, book_pages;
    List<objectBook> obBook;
    CustomerAdapter (Context context, List<objectBook> obBook) {
        this.context = context;
        this.obBook = obBook;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView book_id_txt, book_title_txt, book_author_txt, book_pages_txt;
        LinearLayout mainLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id);
            book_title_txt = itemView.findViewById(R.id.book_title);
            book_author_txt = itemView.findViewById(R.id.book_author);
            book_pages_txt = itemView.findViewById(R.id.book_pages);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

    @NonNull
    @Override
    public CustomerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view1 = inflater.inflate(R.layout.hienthilistbook, parent, false);
        return new MyViewHolder(view1);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.MyViewHolder holder, int position) {

        holder.book_id_txt.setText(String.valueOf((obBook.get(position).getId())));
        holder.book_title_txt.setText(String.valueOf(obBook.get(position).getTitle()));
        holder.book_author_txt.setText(String.valueOf(obBook.get(position).getAuthor()));
        holder.book_pages_txt.setText(String.valueOf(obBook.get(position).getPages()));
        int position1 = position;

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update.class);
                obBook = new objectBook()
//                intent.putExtra("ObjectBook", )
                intent.putExtra("Id", String.valueOf((obBook.get(position1).getId())));
                intent.putExtra("Title", String.valueOf(obBook.get(position1).getTitle()));
                intent.putExtra("Author", String.valueOf(obBook.get(position1).getAuthor()));
                intent.putExtra("Pages", String.valueOf(obBook.get(position1).getPages()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return obBook.size();
    }


}
