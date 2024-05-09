package com.example.librarysystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BookCopiesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_copies);  // Make sure this layout name is correct

        CardView addBookcopy = findViewById(R.id.cardAddBookcopy);
        addBookcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookCopiesActivity .this, AddBookcopyActivity.class);
                it.putExtra("title", "ADD BOOKCOPY");
                startActivity(it);
            }
        });

        CardView updateBookcopy = findViewById(R.id.cardUpdateBookcopy);
        updateBookcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookCopiesActivity .this, UpdateBookcopyActivity.class);
                it.putExtra("title", "UPDATE BOOKCOPY");
                startActivity(it);
            }
        });

        CardView removeBookcopy = findViewById(R.id.cardRemoveBookcopy);
        removeBookcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookCopiesActivity .this, RemoveBookcopyActivity.class);
                it.putExtra("title", "REMOVE BOOKCOPY");
                startActivity(it);
            }
        });

        CardView viewBookcopy = findViewById(R.id.cardViewBookcopy);
        viewBookcopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookCopiesActivity .this, ViewBookcopyActivity.class);
                it.putExtra("title", "VIEW BOOKCOPY");
                startActivity(it);
            }
        });
    }

}

