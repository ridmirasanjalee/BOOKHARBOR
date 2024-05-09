package com.example.librarysystem;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddBookcopyActivity extends AppCompatActivity {

    EditText etBookId, etBranchId, etAccessNo;
    Button btnSave;

    Database database;  // Corrected variable name

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etBookId = findViewById(R.id.etBookId);
        etBranchId = findViewById(R.id.etBranchId);
        etAccessNo = findViewById(R.id.etAccessNo);
        btnSave = findViewById(R.id.btnSave);

        database = new Database(this);  // Corrected initialization

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBookcopy();  // Ensured the click handler works
            }
        });
    }

    private void saveBookcopy() {
        String bookId = etBookId.getText().toString();
        String branchId = etBranchId.getText().toString();
        String accessNo = etAccessNo.getText().toString();

        if (bookId.isEmpty() || branchId.isEmpty() || accessNo.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;  // Return early if any field is empty
        }

        // Use the database helper method for insertion
        long result = database.addBookcopy(bookId, branchId,accessNo);

        if (result == -1) {
            Toast.makeText(this, "Failed to add book", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Book added successfully", Toast.LENGTH_SHORT).show();

            // Clear input fields
            etBookId.setText("");
            etBranchId.setText("");
            etAccessNo.setText("");
        }
    }
}
