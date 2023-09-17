package com.vn.sqllistbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addBook extends AppCompatActivity {

    EditText etTitle, etAuthor, etPages;
    Button btnAddBook;
    sqlListBook dbBook;
    objectBook obBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        setFindId();
        setOneClickBtnAdd();
    }

    private void setOneClickBtnAdd() {
        btnAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obBook = new objectBook(-1, etTitle.getText().toString(), etAuthor.getText().toString(), Integer.parseInt(etPages.getText().toString()));
                dbBook = new sqlListBook(addBook.this);
                dbBook.addOne(obBook);
                Toast.makeText(addBook.this, "Them du lieu thanh cong", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void setFindId() {
        etTitle = findViewById(R.id.update_title_input);
        etAuthor = findViewById(R.id.udpate_anthor_title);
        etPages = findViewById(R.id.update_pages_input);
        btnAddBook = findViewById(R.id.udpate_button);
    }
}