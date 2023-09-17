package com.vn.sqllistbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {

    EditText etUpdateTitle, etUpdateAuthor, etUpdatePages;
    Button btnUpdate;
    String id, Title, Author, Pages;
    objectBook objBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        setIdUpdate();
        getAndSetIntentData();
        UpdateData(objBook);
    }

    private void UpdateData(objectBook objBook) {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlListBook sqlBook = new sqlListBook(update.this);
                Toast.makeText(update.this, "Da nhan" , Toast.LENGTH_SHORT).show();
                sqlBook.updateData(objBook);
            }
        });
    }

    private void setIdUpdate() {
        etUpdateTitle = findViewById(R.id.update_title_input);
        etUpdateAuthor = findViewById(R.id.udpate_anthor_title);
        etUpdatePages = findViewById(R.id.update_pages_input);
        btnUpdate = findViewById(R.id.udpate_button);
    }
    void getAndSetIntentData(){
        if(getIntent().hasExtra("Id") && getIntent().hasExtra("Title") &&
                getIntent().hasExtra("Author") && getIntent().hasExtra("Pages")){
            id = getIntent().getStringExtra("Id");
            Title = getIntent().getStringExtra("Title");
            Author = getIntent().getStringExtra("Author");
            Pages = getIntent().getStringExtra("Pages");
            int id1 = Integer.parseInt(id);
            int pages = Integer.parseInt(Pages);
            objBook = new objectBook(id1, Title, Author, pages);
            //Get and hien thị du lieu
            etUpdateTitle.setText(Title);
            etUpdateAuthor.setText(Author);
            etUpdatePages.setText(Pages);
//            Toast.makeText(this, objBook.toString() , Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }
}