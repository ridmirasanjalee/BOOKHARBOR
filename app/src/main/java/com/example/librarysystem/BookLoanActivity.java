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

public class BookLoanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_loan);  // Make sure this layout name is correct

        CardView addBookloan = findViewById(R.id.cardAddBookLoan);
        addBookloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(BookLoanActivity.this, AddBookloanActivity.class);
                it.putExtra("title", "ADD BOOKLOAN");
                startActivity(it);
            }
        });

        CardView updateBookloan = findViewById(R.id.cardUpdateBookLoan);
        updateBookloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookLoanActivity.this, UpdateBookloanActivity.class);
                it.putExtra("title", "UPDATE BOOKLOAN");
                startActivity(it);
            }
        });

        CardView removeBookloan = findViewById(R.id.cardRemoveBookLoan);
        removeBookloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookLoanActivity.this, RemoveBookloanActivity.class);
                it.putExtra("title", "REMOVE BOOKLOAN");
                startActivity(it);
            }
        });

        CardView viewBookloan = findViewById(R.id.cardViewBookLoan);
        viewBookloan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(BookLoanActivity.this, VeiwBookloanActivity.class);
                it.putExtra("title", "VIEW BOOKLOAN");
                startActivity(it);
            }
        });
    }
}
