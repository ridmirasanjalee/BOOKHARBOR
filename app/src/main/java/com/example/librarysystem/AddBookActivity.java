package com.example.librarysystem;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;  // Missing import

import androidx.appcompat.app.AppCompatActivity;

public class AddBookActivity extends AppCompatActivity {

    EditText etBookId, etTitle, etPublisherName;
    Button btnSave;

    Database database;  // Corrected variable name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etBookId = findViewById(R.id.etBookId);
        etTitle = findViewById(R.id.etTitle);
        etPublisherName = findViewById(R.id.etPublisherName);
        btnSave = findViewById(R.id.btnSave);

        database = new Database(this);  // Corrected initialization

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBook();  // Ensured the click handler works
            }
        });
    }

    private void saveBook() {
        String bookId = etBookId.getText().toString();
        String title = etTitle.getText().toString();
        String publisherName = etPublisherName.getText().toString();

        if (bookId.isEmpty() || title.isEmpty() || publisherName.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;  // Return early if any field is empty
        }

        // Use the database helper method for insertion
        long result = database.addBook(bookId, title, publisherName);

        if (result == -1) {
            Toast.makeText(this, "Failed to add book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etBookId.setText("");
            etTitle.setText("");
            etPublisherName.setText("");
        }
    }
}
