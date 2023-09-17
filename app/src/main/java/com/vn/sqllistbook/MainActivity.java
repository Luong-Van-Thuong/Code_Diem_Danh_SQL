package com.vn.sqllistbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewBook;
    sqlListBook sqlBook;
    CustomerAdapter customerAdapter;
    FloatingActionButton flBtnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFindId();
        display();
        chuyenManHinh();
        suKienXoa();
    }

    private void suKienXoa() {

    }

    private void chuyenManHinh() {
        flBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, addBook.class);
                startActivity(intent);
            }
        });
    }

    private void setFindId() {
        recyclerViewBook = findViewById(R.id.recyclerView);
        flBtnAdd = findViewById(R.id.floatingActionButton);
    }

    //Hiển thị dữ liệu lấy ra từ SQL
    private void display() {
        sqlBook = new sqlListBook(MainActivity.this);
        customerAdapter = new CustomerAdapter(MainActivity.this, sqlBook.getEveryone());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewBook.setLayoutManager(layoutManager);
        recyclerViewBook.setAdapter(customerAdapter);

    }
}