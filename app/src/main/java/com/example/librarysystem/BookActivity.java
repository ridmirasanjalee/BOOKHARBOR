
package com.example.librarysystem;  // Adjust the package according to your structure

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class BookActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);  // Make sure this layout name is correct

        CardView addBook = findViewById(R.id.cardBOAddBook);
        addBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookActivity.this, AddBookActivity.class);
                it.putExtra("title", "ADD BOOK");
                startActivity(it);
            }
        });

        CardView updateBook = findViewById(R.id.cardBOUpdateBook);
        updateBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookActivity.this, UpdateBookActivity.class);
                it.putExtra("title", "UPDATE BOOK");
                startActivity(it);
            }
        });

        CardView removeBook = findViewById(R.id.cardBORemoveBook);
        removeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookActivity.this, RemoveBookActivity.class);
                it.putExtra("title", "REMOVE BOOK");
                startActivity(it);
            }
        });

        CardView viewBook = findViewById(R.id.cardBOViewBook);
        viewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookActivity.this, ViewBookActivity.class);
                it.putExtra("title", "VIEW BOOK");
                startActivity(it);
            }
        });
    }
}
