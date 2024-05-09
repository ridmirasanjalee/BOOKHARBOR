
package com.example.librarysystem;  // Adjust the package according to your structure

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AuthorActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_author);  // Make sure this layout name is correct

        CardView addAuthor = findViewById(R.id.cardAddAuthor);
        addAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(AuthorActivity.this, AddAuthorActivity.class);
                it.putExtra("title", "ADD AUTHOR");
                startActivity(it);
            }
        });

        CardView updateAuthor= findViewById(R.id.cardUpdateAuthor);
        updateAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AuthorActivity.this, UpdateBookActivity.class);
                it.putExtra("title", "UPDATE AUTHOR");
                startActivity(it);
            }
        });

        CardView removeAuthor = findViewById(R.id.cardRemoveAuthor);
        removeAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AuthorActivity.this, RemoveBookActivity.class);
                it.putExtra("title", "REMOVE AUTHOR");
                startActivity(it);
            }
        });

        CardView viewAuthor = findViewById(R.id.cardViewAuthor);
        viewAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(AuthorActivity.this, ViewBookActivity.class);
                it.putExtra("title", "VIEW AUTHOR");
                startActivity(it);
            }
        });
    }
}
